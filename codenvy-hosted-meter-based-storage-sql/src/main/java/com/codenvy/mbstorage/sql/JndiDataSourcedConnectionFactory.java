/*
 * CODENVY CONFIDENTIAL
 * __________________
 * 
 *  [2012] - [2015] Codenvy, S.A. 
 *  All Rights Reserved.
 * 
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.mbstorage.sql;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provide Connections from DataSource binded in JNDI.
 *
 * @author Sergii Kabashniuk
 */
public class JndiDataSourcedConnectionFactory implements ConnectionFactory {
    private final String     jndiLocation;
    private       DataSource dataSource;

    @Inject
    public JndiDataSourcedConnectionFactory(@Named String jndiLocation) {
        this.jndiLocation = jndiLocation;
    }

    @PostConstruct
    public void init() throws NamingException {
        dataSource = (DataSource)new InitialContext().lookup(jndiLocation);
        if (dataSource == null) {
            throw new RuntimeException("Data source is not configured in jndi location " + jndiLocation);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
