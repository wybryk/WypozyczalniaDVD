package pl.bazadanych.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import pl.bazadanych.tables.BaseTable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mateusz on 2017-04-20.
 */
public abstract class GeneralDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(GeneralDao.class);
    protected final ConnectionSource connectionSource;

    public GeneralDao(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    public <T extends BaseTable, I> Dao<T, I> getDao(Class<T> clas) {
        try {
            return DaoManager.createDao(connectionSource, clas);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return null;
    }

    public <T extends BaseTable, I> void createOrUpdateTabel(BaseTable baseTable){
        Dao<T, I> dao = getDao((Class<T>) baseTable.getClass());
        try {
            dao.createOrUpdate((T) baseTable);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        finally{
            this.closeDaoConnection();
        }
    }
    public <T extends BaseTable, I> void createTabel(BaseTable baseTable){
        Dao<T, I> dao = getDao((Class<T>) baseTable.getClass());
        try {
            dao.create((T) baseTable);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        finally{
            this.closeDaoConnection();
        }
    }

    public <T extends BaseTable, I> void refresh(BaseTable baseTable){
        try {
            Dao<T, I> dao = getDao((Class<T>) baseTable.getClass());
            dao.refresh((T) baseTable);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        finally{
            this.closeDaoConnection();
        }
    }

    public <T extends BaseTable, I> void delete(BaseTable baseTable){
        try {
            Dao<T, I> dao = getDao((Class<T>) baseTable.getClass());
            dao.delete((T) baseTable);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        finally{
            this.closeDaoConnection();
        }
    }

    public <T extends BaseTable, I> List<T> queryforAll(Class<T> clas) {
        try {
            Dao<T, I> dao = getDao(clas);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        finally{
            this.closeDaoConnection();
        }
        return null;
    }
    public <T extends  BaseTable, I> T findById(Class<T> clas, Integer id){
        Dao<T, I> dao = getDao(clas);
        try {
            return dao.queryForId((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        finally{
            this.closeDaoConnection();
        }
        return null;
    }
    private void closeDaoConnection(){
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
