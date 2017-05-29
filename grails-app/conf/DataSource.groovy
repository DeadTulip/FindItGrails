dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "root"
    password = "root"
    //loggingSql = true
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

environments {
    development {
        dataSource {
            url = "jdbc:mysql://127.0.0.1:3306/findit?autoReconnect=true&useSSL=false"
        }
    }
    test {
        dataSource {
            url = "jdbc:mysql://127.0.0.1:3306/findit_test?autoReconnect=true&useSSL=false"
        }
    }
}
