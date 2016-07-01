/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.reusable.pool.base.testimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.digispherecorp.api.reusable.pool.base.AbstractReusable;

/**
 *
 * @author Walle
 */
public class JDBCConnectionReusable extends AbstractReusable<Connection> {

    @Override
    public void expire() {
        try {
            reusableObj.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnectionReusable.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
        }
        super.expire();
    }

}
