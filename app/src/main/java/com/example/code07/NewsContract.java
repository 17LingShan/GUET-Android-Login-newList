package com.example.code07;

import android.provider.BaseColumns;

public class NewsContract {
    private NewsContract() {}

    public static class NewsEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbl_news";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_IMAGE = "image";
    }
}
