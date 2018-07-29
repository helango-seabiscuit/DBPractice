//package com.hemalatha.db.performance;
//
//import com.mysql.cj.jdbc.MysqlDataSource;
//
//import javax.sql.DataSource;
//
//public class SqlDataProvider {
//
//    private boolean rewriteBatchedStatements = true;
//
//    public String hibernateDialect() {
//        return "org.hibernate.dialect.MySQL57InnoDBDialect";
//    }
//
//    public DataSource dataSource() {
//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setURL("jdbc:mysql://localhost/high_performance_java_persistence?" +
//                         "&rewriteBatchedStatements="+rewriteBatchedStatements);
//        dataSource.setUser("ns");
//        dataSource.setPassword("ns");
//        return dataSource;
//    }
//
//    public Class<? extends DataSource> dataSourceClassName() {
//        return MysqlDataSource.class;
//    }
//
//    public String url() {
//        return "jdbc:mysql://localhost/high_performance_java_persistence?user=" + username() + "&password=" + password();
//    }
//
//    public String username() {
//        return "ns";
//    }
//
//    public String password() {
//        return "ns";
//    }
//
//
//}
