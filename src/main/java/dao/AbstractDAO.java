package dao;

import java.lang.reflect.*;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.beans.PropertyDescriptor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * The type Abstract dao - defines the common operations for accessing a table: Insert, Update, Delete,
 * FindById, FindAll. The operations are defined on the specified generic type T which can be any Java Model Class that is
 * mapped to the Database, and has the same name as the table and the same instance variables and data types as the table fields.
 * @param <T> the type parameter - can be Client, Product or Order
 * @author Ariana Horvath
 */
public class AbstractDAO<T> {

    /**
     * The constant LOGGER.
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    /**
     * Instantiates a new Abstract dao.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * creates the select query
     * @param field string - the field for SELECT
     * @return the query string
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM orders_db.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<T> findAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM orders_db.");
        sb.append(type.getSimpleName().toLowerCase());
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = sb.toString();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Create objects list.
     *
     * @param resultSet the result set returned by query
     * @return the list of objects
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Create insert query string.
     *
     * @param t the t
     * @return the string
     */
    public String createInsertQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO orders_db.");
        sb.append(type.getSimpleName());
        sb.append("(");
        String prefix = "";
        for (Field field : type.getDeclaredFields()) {
            String fieldName = field.getName();
            sb.append(prefix);
            prefix = ",";
            sb.append(fieldName);
        }
        sb.append(") VALUES(");
        prefix = "";
        for (Field field : type.getDeclaredFields()) {
            Object value;
            field.setAccessible(true);
            try {
                value = field.get(t);
                sb.append(prefix);
                prefix = ",";
                if(value instanceof String)
                    sb.append("'");
                sb.append(value.toString());
                if(value instanceof String)
                    sb.append("'");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.append(");");
        return sb.toString();
    }

    /**
     * Insert t.
     *
     * @param t the t
     * @return the t
     */
    public T insert(T t) {
        String query = createInsertQuery(t);
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * Create update query string.
     *
     * @param t the t
     * @return the string
     */
    public String createUpdateQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE orders_db.");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        String prefix = "";
        Object value;
        try {
            for (Field field : type.getDeclaredFields()) {
                String fieldName = field.getName();
                field.setAccessible(true);
                sb.append(prefix);
                prefix = ",";
                sb.append(fieldName);
                sb.append(" = ");

                    value = field.get(t);
                    if(value instanceof String)
                        sb.append("'");
                    sb.append(value.toString());
                    if(value instanceof String)
                        sb.append("'");
                }
            sb.append(" WHERE id = ");
            Field idField = type.getDeclaredFields()[0];
            idField.setAccessible(true);
            value = idField.get(t);
            sb.append(value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * Update t.
     *
     * @param t the t
     * @return the t
     */
    public T update(T t) {
        String query = createUpdateQuery(t);
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * Create delete query string.
     *
     * @param t the t
     * @return the string
     */
    public String createDeleteQuery(T t){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM orders_db.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = ");
        Field idField = type.getDeclaredFields()[0];
        idField.setAccessible(true);
        Object value;
        try {
            value = idField.get(t);
            sb.append(value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * Delete t.
     *
     * @param t the t
     * @return the t
     */
    public T delete(T t) {
        String query = createDeleteQuery(t);
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * Create table j table.
     *
     * @param list the list
     * @return the j table
     */
    public JTable createTable(List<T> list) {
        ArrayList<String> columns = new ArrayList<>();
        for(Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            columns.add(field.getName());
        }
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns.toArray());
        for(Object obj : list) {
            ArrayList<Object> objects = new ArrayList<>();
            for(Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    objects.add(field.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(objects.toArray());
        }
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        return table;
    }
}
