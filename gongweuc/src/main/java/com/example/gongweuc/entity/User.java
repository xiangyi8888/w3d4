package com.example.gongweuc.entity;

import java.util.List;

public class User {
    public String code;
    public String msg;
    public List<DataBase> data;
    public class DataBase{
        public String sellerName;
        public String sellerid;
        public boolean checkbox;
        public List<Product> list;
        public class  Product{
            public String title;
            public String images;
            public double price;
            public boolean isproductcheckbox;
            public String pid;
            public int pos;
            public int productNum;
        }
    }
}
