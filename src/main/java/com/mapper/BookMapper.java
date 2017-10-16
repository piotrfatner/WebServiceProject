package com.mapper;

import com.dto.BookDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<BookDTO> {
    @Override
    public BookDTO mapRow(ResultSet rs, int i) throws SQLException {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setDateOfHire(rs.getString("DATE_OF_HIRE"));
        bookDTO.setStatusOfHire(rs.getString("STATUS_"));
        bookDTO.setTitle(rs.getString("TITLE"));
        bookDTO.setAuthor(rs.getString("AUTHOR"));
        bookDTO.setiSBN(rs.getString("ISBN"));
        bookDTO.setBookGenre(rs.getString("BOOK_GENRE"));
        bookDTO.setBookId(rs.getLong("BOOK_ID"));
        return bookDTO;
    }
}
