package com.smoothstack.december.librarianService.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.smoothstack.december.librarianService.entity.Publisher;

@Component
public class PublisherDAO extends BaseDAO<Publisher> implements ResultSetExtractor<List<Publisher>> {
    
    public Integer createPublisher(String publisherName, String publisherAddress, String publisherPhone) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?, ?, ?);";
        Object[] parameters = new Object[] {publisherName, publisherAddress, publisherPhone};
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.mySQLTemplate.update(psc, keyHolder);
        return (Integer)keyHolder.getKey();
    }
    
    public List<Publisher> getPublishers() throws ClassNotFoundException, SQLException {
        return super.mySQLTemplate.query("SELECT * FROM tbl_publisher;", this);
    }
    
    public Publisher getPublisher(int publisherId) throws ClassNotFoundException, SQLException {
        List<Publisher> publishers = super.mySQLTemplate.query("SELECT * FROM tbl_publisher WHERE publisherId = ?;",
                new Object[] {publisherId}, this);
        Publisher publisher = null;
        
        if (publishers.size() != 0) {
            publisher = publishers.get(0);
        }
        
        return publisher;
    }
    
    public void updatePublisher(int publisherId, String publisherName, String publisherAddress, String publisherPhone) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("UPDATE tbl_publisher SET publisherName=?, publisherAddress=?, publisherPhone=? WHERE publisherId = ?",
                new Object[] {publisherName, publisherAddress, publisherPhone, publisherId});
    }
    
    public void deletePublisher(int publisherId) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("DELETE FROM tbl_publisher WHERE publisherId = ?;",
                new Object[] {publisherId});
    }

    @Override
    public List<Publisher> extractData(ResultSet rs) throws SQLException {
        List<Publisher> publishers = new ArrayList<>();
        
        while (rs.next()) {
            Publisher publisher = new Publisher();
            publisher.setPublisherId(rs.getInt("publisherId"));
            publisher.setName(rs.getString("publisherName"));
            publisher.setAddress(rs.getString("publisherAddress"));
            publisher.setPhoneNumber(rs.getString("publisherPhone"));
            publishers.add(publisher);
        }
        
        return publishers;
    }

}
