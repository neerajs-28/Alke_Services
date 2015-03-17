package com.alke.dao;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.alke.services.beans.Client;
import com.alke.services.beans.Invoice;
import com.alke.services.beans.Item;
import com.alke.services.beans.Tax;
import com.alke.util.MonitorUtil;

@Repository 
public class InvoiceDao {

	@Autowired
	private JdbcTemplate invoiceTemplate;
	
	public List<Invoice>  getInvoiceSummary(int year, String userId) {
		
		String sql = "select order_id,invoice_id,invoide_date,invoice_cost,invoice_pay_date,invoice_order_status,invoice_pay_status,po_id,po_date,challan_id,challan_date,rating,download_link " +
				"       from alke_test.invoice_summary where year=? and client_id = (select client_id from userPassrd where username =?)";
		
		
		List<Invoice> invoiceList= invoiceTemplate.query(sql,new Object[]{year,userId}, new InvoiceSummaryRowMapper());
		return invoiceList;
	}
	
   public Invoice  getInvoiceSummaryById(int year, String invoiceId) {
		
		String sql = "select order_id,invoice_id,invoide_date,invoice_cost,invoice_pay_date,invoice_order_status,invoice_pay_status,po_id,po_date,challan_id,challan_date,rating,download_link " +
				"       from alke_test.invoice_summary where year=? and invoice_id = ?";
		
		
		Invoice invoice= invoiceTemplate.queryForObject(sql,new Object[]{year,invoiceId}, new InvoiceSummaryRowMapper());
		return invoice;
	}
	
   public void storePDF(final InputStream is,final int orderid,final String fileName,int length) {
	   
	String sql ="INSERT INTO alke_test.invoice_pdf(order_id,name, file) VALUES(?,?,?)";
	LobHandler lobHandler = new DefaultLobHandler();
	invoiceTemplate.update(sql, new Object[]{orderid,fileName,new SqlLobValue(is, length, lobHandler)},new int[]{Types.INTEGER,Types.CHAR,Types.BLOB});
     System.out.println("insert");
   }
   
   public InputStream getBlobData(String invoiceId, int year, long clientId)
   {
           String sql = " SELECT file FROM alke_test.invoice_pdf WHERE order_id = (select order_id from alke_test.invoice_summary where invoice_id =? and year =? and client_id =?) ";
           return invoiceTemplate.queryForObject(sql, new Object[]{invoiceId,year,clientId},new FileMapper());
   }

   public long getClientId(String userId) {
	   
	   String sql = " SELECT client_id FROM alke_test.userpassrd WHERE username = ? ";
	   
	   return invoiceTemplate.queryForObject(sql, new Object[]{userId},Long.class);
   }
   
   class FileMapper implements ParameterizedRowMapper<InputStream>
   {
           public InputStream mapRow(ResultSet rs, int rowNum) throws SQLException
           {

                   return rs.getBinaryStream("file");
           }
   }
   
   
	public Client getClient (String userId) {
		
		String sql =  "select name,address,phone_no,email_id,website,contact_person,cst_no,vat_no from alke_test.client_info where id =(select client_id from userPassrd where username =?)";
		Client client =invoiceTemplate.queryForObject(sql, new Object[]{userId},new ClientRowMapper());
		return client;
	}
	
	public List<Item> getItems(String invoiceId, int year) {
		
		String sql ="select item_name,rate,quantity,total,s.invoice_cost from alke_test.items i join invoice_summary s on i.order_id =s.order_id and invoice_id =? and year =?";
		
		List<Item> items= invoiceTemplate.query(sql,new Object[]{invoiceId,year}, new ItemMapper());
		return items;
		
	}
	
	public List<Tax> getTaxSummary(String invoiceId, int year) {
		
		String sql ="select t.tax_details,t.tax_calc,t.cost from alke_test.tax_summary t join invoice_summary s on t.order_id =s.order_id and invoice_id = ? and year = ?";
		
		List<Tax> taxSummary= invoiceTemplate.query(sql,new Object[]{invoiceId,year}, new TaxSummaryMapper());
		return taxSummary;
		
	}
	
	
	private class ItemMapper implements RowMapper<Item> {

		@Override
		public Item mapRow(ResultSet rs, int arg1) throws SQLException {
			
			Item item = new Item();
			item.setItemName(rs.getString("item_name"));
			item.setRate(rs.getDouble("rate"));
			item.setQty(rs.getDouble("quantity"));
			item.setTotal(rs.getDouble("total"));
			return item;
		}
		
		
	}
	
	private class TaxSummaryMapper implements RowMapper<Tax> {

		@Override
		public Tax mapRow(ResultSet rs, int arg1) throws SQLException {
			
			Tax tax = new Tax();
			tax.setCalc(rs.getDouble("tax_calc"));
			tax.setTaxName(rs.getString("tax_details"));
			tax.setTaxAmounted(rs.getDouble("cost"));
			return tax;
		}
		
		
	}
	private class InvoiceSummaryRowMapper implements RowMapper<Invoice> {

		@Override
		public Invoice mapRow(ResultSet rs, int arg1) throws SQLException {
			
			
			Invoice invoice = new Invoice();
			invoice.setInvoicedate(rs.getDate("invoide_date"));
			invoice.setInvoiceCost(rs.getDouble("invoice_cost"));
			invoice.setInvoiceId(rs.getString("invoice_id"));
			invoice.setInvoicePayDate(rs.getDate("invoice_pay_date"));
			invoice.setInvoicePayStatus(MonitorUtil.STATUS.get(rs.getInt("invoice_order_status")));
			invoice.setInvoiceOrdrStatus(MonitorUtil.STATUS.get(rs.getInt("invoice_order_status")));
			invoice.setDownloadLink(rs.getString("download_link"));
			invoice.setPo(rs.getString("po_id"));
			invoice.setPoDate(rs.getDate("po_date"));
			
			
			
			
			return invoice;
		}
	}
		private class ClientRowMapper implements RowMapper<Client> {

			@Override
			public Client mapRow(ResultSet rs, int arg1) throws SQLException {
				
				
				Client client = new Client();
				client.setName(rs.getString("name"));
				client.setAddress(rs.getString("address"));
				client.setEmail(rs.getString("email_id"));
				client.setWebsite(rs.getString("website"));
				client.setContactPerson(rs.getString("contact_person"));
				client.setCstNo(rs.getString("cst_no"));
				client.setVatNo(rs.getString("vat_no"));
				return client;
			}
		
	}
}
