package vn.edu.nlu.it.ltw.group8.ttltw_donghocaocap.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    // ==========================================
    // CÁC TRƯỜNG KHỚP VỚI DATABASE MỚI (nola2hand_db)
    // ==========================================
    private int id;             // Map với UserID
    private String username;    // Map với Username
    private String password;    // Map với PasswordHash
    private String fullName;    // Map với FullName
    private String email;       // Map với Email
    private String phone;       // Map với Phone
    private String avatar;      // Map với AvatarURL
    private String role;        // Map với Role ('Buyer', 'Seller', 'SystemAdmin',...)
    private boolean isLocked;   // Map với IsLocked (Đã gộp chung status và active cũ vào đây)
    private Timestamp createdAt;// Map với CreatedAt

    // ==========================================
    // CÁC TRƯỜNG GIỮ LẠI CHO LOGIC ĐĂNG KÝ CŨ
    // ==========================================
    private String verificationToken;
    private Timestamp verificationExpiry;

    public User() {
    }

    public User(int id, String username, String password, String fullName, String email, String phone, String avatar, String role, boolean isLocked, Timestamp createdAt, String verificationToken, Timestamp verificationExpiry) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.role = role;
        this.isLocked = isLocked;
        this.createdAt = createdAt;
        this.verificationToken = verificationToken;
        this.verificationExpiry = verificationExpiry;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isLocked() { return isLocked; }
    public void setLocked(boolean locked) { isLocked = locked; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public String getVerificationToken() { return verificationToken; }
    public void setVerificationToken(String verificationToken) { this.verificationToken = verificationToken; }

    public Timestamp getVerificationExpiry() { return verificationExpiry; }
    public void setVerificationExpiry(Timestamp verificationExpiry) { this.verificationExpiry = verificationExpiry; }
}