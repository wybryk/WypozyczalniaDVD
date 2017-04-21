package pl.bazadanych.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import pl.bazadanych.tabels.BaseTabel;

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

    public <T extends BaseTabel, I> Dao<T, I> getDao(Class<T> clas) {
        try {
            return DaoManager.createDao(connectionSource, clas);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return null;
    }

    public <T extends BaseTabel, I> void createOrUpdateTabel(BaseTabel baseTabel){
        Dao<T, I> dao = getDao((Class<T>) baseTabel.getClass());
        try {
            dao.createOrUpdate((T)baseTabel);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public <T extends BaseTabel, I> void refresh(BaseTabel baseTabel){
        try {
            Dao<T, I> dao = getDao((Class<T>) baseTabel.getClass());
            dao.refresh((T) baseTabel);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public <T extends BaseTabel, I> void delete(BaseTabel baseTabel){
        try {
            Dao<T, I> dao = getDao((Class<T>) baseTabel.getClass());
            dao.delete((T) baseTabel);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public <T extends BaseTabel, I> List<T> queryforAll(Class<T> clas) {
        try {
            Dao<T, I> dao = getDao(clas);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return null;
    }
}
