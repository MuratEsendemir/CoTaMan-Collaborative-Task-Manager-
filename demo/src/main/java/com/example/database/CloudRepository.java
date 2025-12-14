package com.example.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class CloudRepository {
    // Koleksiyonlar (Tablolar)
    private final MongoCollection<User> userCollection;
    private final MongoCollection<Group> groupCollection;
    private final MongoCollection<Event> eventCollection;
    private final MongoCollection<ArchiveFile> fileCollection;

    public CloudRepository() {
        // Veritabanı bağlantısını al
        MongoDatabase db = MongoConnectionManager.getInstance().getDatabase();
        
        // Tabloları bağla (POJO mapping sayesinde User.class vb. kullanabiliyoruz)
        this.userCollection = db.getCollection("users", User.class);
        this.groupCollection = db.getCollection("groups", Group.class);
        this.eventCollection = db.getCollection("events", Event.class);
        this.fileCollection = db.getCollection("files", ArchiveFile.class);
    }

    // ==========================================
    // 1. KULLANICI İŞLEMLERİ (USER)
    // ==========================================

    // Yeni kullanıcı kaydet
    public void saveUser(User user) {
        // Aynı email var mı kontrol et (Basit validasyon)
        User existing = getUserByEmail(user.getEmail());
        if (existing == null) {
            userCollection.insertOne(user);
            System.out.println("Kullanıcı kaydedildi: " + user.getName());
        } else {
            System.out.println("HATA: Bu email zaten kayıtlı!");
        }
    }

    // Giriş yaparken kullanılır: Email'e göre kullanıcı getir
    public User getUserByEmail(String email) {
        return userCollection.find(Filters.eq("email", email)).first();
    }

    // ID'ye göre kullanıcı getir
    public User getUserById(ObjectId id) {
        return userCollection.find(Filters.eq("_id", id)).first();
    }

    // ==========================================
    // 2. ETKİNLİK İŞLEMLERİ (EVENT / CALENDAR)
    // ==========================================

    // Yeni ders/sınav ekle
    public void saveEvent(Event event) {
        eventCollection.insertOne(event);
        System.out.println("Etkinlik eklendi: " + event.getTitle());
    }

    // Bir kullanıcının TÜM etkinliklerini getir (Kendi takvimi için)
    public List<Event> getEventsForUser(ObjectId userId) {
        List<Event> events = new ArrayList<>();
        // "ownerId" alanı, aradığımız userId'ye eşit olanları bul
        eventCollection.find(Filters.eq("ownerId", userId)).into(events);
        return events;
    }

    // Etkinliği Sil
    public void deleteEvent(ObjectId eventId) {
        eventCollection.deleteOne(Filters.eq("_id", eventId));
        System.out.println("Etkinlik silindi.");
    }

    // ==========================================
    // 3. GRUP İŞLEMLERİ (GROUP)
    // ==========================================

    // Grup oluştur
    public void createGroup(Group group) {
        groupCollection.insertOne(group);
        System.out.println("Grup oluşturuldu: " + group.getName());
    }

    // Gruba üye ekle (Push Update)
    public void addMemberToGroup(ObjectId groupId, ObjectId newMemberId) {
        groupCollection.updateOne(
            Filters.eq("_id", groupId),
            Updates.push("memberIds", newMemberId) // Listeye ekle
        );
        System.out.println("Üye gruba eklendi.");
    }

    // Kullanıcının üye olduğu grupları getir
    public List<Group> getGroupsForUser(ObjectId userId) {
        List<Group> groups = new ArrayList<>();
        // memberIds listesinin içinde userId geçen grupları bul
        groupCollection.find(Filters.in("memberIds", userId)).into(groups);
        return groups;
    }

    // ==========================================
    // 4. DOSYA İŞLEMLERİ (ARCHIVE)
    // ==========================================

    public void saveFileMetadata(ArchiveFile file) {
        fileCollection.insertOne(file);
    }

    // Herkese açık dosyaları getir
    public List<ArchiveFile> getPublicFiles() {
        List<ArchiveFile> files = new ArrayList<>();
        fileCollection.find(Filters.eq("visibility", Visibility.PUBLIC)).into(files);
        return files;
    }

    // Bir grubun dosyalarını getir
    // (Not: ArchiveFile sınıfına 'groupId' alanı eklemen gerekebilir, şimdilik uploaderId üzerinden örnek veriyorum)
    public List<ArchiveFile> getFilesUploadedByUser(ObjectId uploaderId) {
        List<ArchiveFile> files = new ArrayList<>();
        fileCollection.find(Filters.eq("uploaderId", uploaderId)).into(files);
        return files;
    }
}