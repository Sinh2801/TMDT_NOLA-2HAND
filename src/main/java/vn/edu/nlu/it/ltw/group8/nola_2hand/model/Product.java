package vn.edu.nlu.it.ltw.group8.nola_2hand.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    // CÁC TRƯỜNG KHỚP VỚI DATABASE
    private int productId;
    private int sellerId; // Bắt buộc: Người bán
    private int brandId;
    private int categoryId;
    private String name;
    private String sku;
    private String description;
    private double originalPrice;
    private double currentPrice; // Giá 2hand
    private int stockQuantity; // Mặc định là 1 với đồ cũ
    private int conditionPercentage; // Độ mới (VD: 90, 95)
    private boolean isAuthenticated;

    // Bổ sung các trường tính năng đẩy tin và thống kê
    private boolean isBoosted;
    private Timestamp boostUntil;
    private int viewCount;

    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt; // Bổ sung thời gian cập nhật

    // CÁC BIẾN PHỤ HIỂN THỊ UI (Dùng khi JOIN bảng)
    private String sellerName;
    private String primaryImage;

    public Product() {
    }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getSellerId() { return sellerId; }
    public void setSellerId(int sellerId) { this.sellerId = sellerId; }

    public int getBrandId() { return brandId; }
    public void setBrandId(int brandId) { this.brandId = brandId; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getOriginalPrice() { return originalPrice; }
    public void setOriginalPrice(double originalPrice) { this.originalPrice = originalPrice; }

    public double getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public int getConditionPercentage() { return conditionPercentage; }
    public void setConditionPercentage(int conditionPercentage) { this.conditionPercentage = conditionPercentage; }

    public boolean isAuthenticated() { return isAuthenticated; }
    public void setAuthenticated(boolean isAuthenticated) { this.isAuthenticated = isAuthenticated; }

    public boolean isBoosted() { return isBoosted; }
    public void setBoosted(boolean boosted) { isBoosted = boosted; }

    public Timestamp getBoostUntil() { return boostUntil; }
    public void setBoostUntil(Timestamp boostUntil) { this.boostUntil = boostUntil; }

    public int getViewCount() { return viewCount; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public String getSellerName() { return sellerName; }
    public void setSellerName(String sellerName) { this.sellerName = sellerName; }

    public String getPrimaryImage() { return primaryImage; }
    public void setPrimaryImage(String primaryImage) { this.primaryImage = primaryImage; }
}