package com.nisum.csvtojson;

public class Sales {
    private String Region;
    private String Country;
    private String ItemType;
    private String SalesChannel;
    private String OrderPriority;
    private String OrderDate;
    private String OrderId;
    private String ShipDate;
    private String UnitSold;
    private String UnitPrice;
    private String UnitCost;
    private String TotalRevenue;
    private String TotalCost;
    private String TotalProfit;

    @Override
    public String toString() {
        return "Sales{" +
                "Region='" + Region + '\'' +
                ", Country='" + Country + '\'' +
                ", ItemType='" + ItemType + '\'' +
                ", SalesChannel='" + SalesChannel + '\'' +
                ", OrderPriority='" + OrderPriority + '\'' +
                ", OrderDate='" + OrderDate + '\'' +
                ", OrderId='" + OrderId + '\'' +
                ", ShipDate='" + ShipDate + '\'' +
                ", UnitSold='" + UnitSold + '\'' +
                ", UnitPrice='" + UnitPrice + '\'' +
                ", UnitCost='" + UnitCost + '\'' +
                ", TotalRevenue='" + TotalRevenue + '\'' +
                ", TotalCost='" + TotalCost + '\'' +
                ", TotalProfit='" + TotalProfit + '\'' +
                '}';
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }

    public String getSalesChannel() {
        return SalesChannel;
    }

    public void setSalesChannel(String salesChannel) {
        SalesChannel = salesChannel;
    }

    public String getOrderPriority() {
        return OrderPriority;
    }

    public void setOrderPriority(String orderPriority) {
        OrderPriority = orderPriority;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getShipDate() {
        return ShipDate;
    }

    public void setShipDate(String shipDate) {
        ShipDate = shipDate;
    }

    public String getUnitSold() {
        return UnitSold;
    }

    public void setUnitSold(String unitSold) {
        UnitSold = unitSold;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getUnitCost() {
        return UnitCost;
    }

    public void setUnitCost(String unitCost) {
        UnitCost = unitCost;
    }

    public String getTotalRevenue() {
        return TotalRevenue;
    }

    public void setTotalRevenue(String totalRevenue) {
        TotalRevenue = totalRevenue;
    }

    public String getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(String totalCost) {
        TotalCost = totalCost;
    }

    public String getTotalProfit() {
        return TotalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        TotalProfit = totalProfit;
    }

    public Sales(String region, String country, String itemType, String salesChannel, String orderPriority, String orderDate, String orderId, String shipDate, String unitSold, String unitPrice, String unitCost, String totalRevenue, String totalCost, String totalProfit) {
        Region = region;
        Country = country;
        ItemType = itemType;
        SalesChannel = salesChannel;
        OrderPriority = orderPriority;
        OrderDate = orderDate;
        OrderId = orderId;
        ShipDate = shipDate;
        UnitSold = unitSold;
        UnitPrice = unitPrice;
        UnitCost = unitCost;
        TotalRevenue = totalRevenue;
        TotalCost = totalCost;
        TotalProfit = totalProfit;
    }

    public Sales() {
    }


}
