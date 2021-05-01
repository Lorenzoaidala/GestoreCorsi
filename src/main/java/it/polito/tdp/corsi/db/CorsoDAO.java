package it.polito.tdp.corsi.db;

import java.sql.*;
import java.util.*;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {

	public List<Corso> getCorsiByPeriodo(Integer periodo){
		String sql ="SELECT * "
				+ "FROM corso "
				+ "WHERE pd = ?";

		List <Corso> result = new ArrayList<Corso>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			conn.close(); // importante chiudere la connessione

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public Map<Corso,Integer> getIscrittiByPeriodo(Integer pd){
		String sql ="SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) AS tot "
				+"FROM corso c, iscrizione i "
				+"WHERE c.codins = i.codins AND c.pd=? "
				+"GROUP BY c.codins, c.nome, c.crediti, c.pd";
		Map <Corso,Integer> result = new HashMap<Corso,Integer>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"), rs.getInt("pd"));
				Integer n = rs.getInt("tot");
				result.put(c,n);
			}
			conn.close(); // importante chiudere la connessione

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}




