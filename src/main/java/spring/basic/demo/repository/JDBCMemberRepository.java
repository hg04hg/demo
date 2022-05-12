package spring.basic.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.basic.demo.domain.Member;

import javax.sql.DataSource;
import java.sql.*;

public class JDBCMemberRepository implements MemberRepositoryInterface{

    private DataSource dataSource; // Spring bean
    public int index = 1;

    @Autowired
    public JDBCMemberRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void saveMember(Member member) {
        // 객체에 id값 주고 
        // DB에 저장

        // String sql = "INSERT INTO member(id, name) values(?, ?)";
        String sql = "INSERT INTO member(name) values(?)"; // identity

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // identity

            // member.setId(index++);

            // pstmt.setInt(1, member.getId());
            pstmt.setString(1, member.getName());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        close(con);

    }

    @Override
    public Member findById(int id) {

        String sql = "SELECT * FROM member WHERE id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        ResultSet rs = null;

        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if(rs.next()){
                Member m = new Member();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                return m;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // try, catch 문을 다 실행하고 떠나기 전에 무조건 실행하는 코드
            close(con);
        }
        return null;
    }

    private void close(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
