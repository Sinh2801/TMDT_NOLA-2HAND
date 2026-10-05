package vn.edu.nlu.it.ltw.group8.ttltw_donghocaocap.dao;

import vn.edu.nlu.it.ltw.group8.ttltw_donghocaocap.context.DBContext;
import vn.edu.nlu.it.ltw.group8.ttltw_donghocaocap.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // 1. Người bán đăng tin mới
    public boolean insertProduct(Product p) {
        String query = "INSERT INTO products (SellerID, CategoryID, Name, Description, CurrentPrice, ConditionPercentage, StockQuantity, Status, CreatedAt) " +
                "VALUES (?, ?, ?, ?, ?, ?, 1, 'Pending', NOW())";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, p.getSellerId());
            ps.setInt(2, p.getCategoryId());
            ps.setString(3, p.getName());
            ps.setString(4, p.getDescription());
            ps.setDouble(5, p.getCurrentPrice());
            ps.setInt(6, p.getConditionPercentage());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    // 2. Lấy danh sách sản phẩm Active ra trang chủ (Ưu tiên tin IsBoosted)
    public List<Product> getActiveProducts() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.*, u.FullName AS SellerName, " +
                "(SELECT ImageURL FROM productimages pi WHERE pi.ProductID = p.ProductID AND pi.IsPrimary = 1 LIMIT 1) AS PrimaryImage " +
                "FROM products p " +
                "JOIN users u ON p.SellerID = u.UserID " +
                "WHERE p.Status = 'Active' AND p.StockQuantity > 0 " +
                "ORDER BY p.IsBoosted DESC, p.CreatedAt DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    // 3. Xem chi tiết 1 sản phẩm
    public Product getProductById(int productId) {
        String query = "SELECT p.*, u.FullName AS SellerName, " +
                "(SELECT ImageURL FROM productimages pi WHERE pi.ProductID = p.ProductID AND pi.IsPrimary = 1 LIMIT 1) AS PrimaryImage " +
                "FROM products p " +
                "JOIN users u ON p.SellerID = u.UserID " +
                "WHERE p.ProductID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return mapProduct(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return null;
    }

    // Hàm dùng chung để map dữ liệu từ ResultSet sang Model
    private Product mapProduct(ResultSet rs) throws Exception {
        Product p = new Product();
        p.setProductId(rs.getInt("ProductID"));
        p.setSellerId(rs.getInt("SellerID"));
        p.setBrandId(rs.getInt("BrandID"));
        p.setCategoryId(rs.getInt("CategoryID"));
        p.setName(rs.getString("Name"));
        p.setSku(rs.getString("SKU"));
        p.setDescription(rs.getString("Description"));
        p.setOriginalPrice(rs.getDouble("OriginalPrice"));
        p.setCurrentPrice(rs.getDouble("CurrentPrice"));
        p.setStockQuantity(rs.getInt("StockQuantity"));
        p.setConditionPercentage(rs.getInt("ConditionPercentage"));
        p.setAuthenticated(rs.getBoolean("IsAuthenticated"));
        p.setBoosted(rs.getBoolean("IsBoosted"));
        p.setBoostUntil(rs.getTimestamp("BoostUntil"));
        p.setViewCount(rs.getInt("ViewCount"));
        p.setStatus(rs.getString("Status"));
        p.setCreatedAt(rs.getTimestamp("CreatedAt"));
        p.setUpdatedAt(rs.getTimestamp("UpdatedAt"));

        // Các trường JOIN
        p.setSellerName(rs.getString("SellerName"));
        p.setPrimaryImage(rs.getString("PrimaryImage"));
        return p;
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}