package com.alke.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alke.dao.InvoiceDao;
import com.alke.services.beans.Client;
import com.alke.services.beans.Invoice;
import com.alke.services.beans.Item;
import com.alke.services.beans.Tax;
import com.alke.services.response.ClientRes;
import com.alke.services.response.InvoiceRes;
import com.alke.services.response.InvoiceSummary;
import com.alke.services.response.InvoiceVO;
 

@Service
public class TestService {
	
	@Autowired
	private InvoiceDao invoiceDao;

	@GET
	@Produces("application/json")
	@Path("/invoice/summary/{year}/{userid}")
	public InvoiceSummary  getService(@PathParam("year") int year,@PathParam("userid") String userId) {
		

		List<Invoice> invoiceList = invoiceDao.getInvoiceSummary(year, userId);
		InvoiceSummary is = new InvoiceSummary();
		is.setInvoice(invoiceList);
		is.setSuccess("true");
		return is;
	}
	
	@GET
	@Produces("application/json")
	@Path("/client/{userid}")
	public ClientRes  getService(@PathParam("userid") String userId) {
		
		ClientRes clientRes = new ClientRes();
		Client client = invoiceDao.getClient(userId);
		clientRes.setClient(client);
		clientRes.setSuccess("true");
		return clientRes;
	}

	@GET
	@Produces("application/json")
	@Path("/invoice/{userid}/{year}/{invid}")
	public InvoiceRes  getItemSummary(@PathParam("userid") String userId, @PathParam("year") Integer year, @PathParam("invid") String invoiceId) {
		
		InvoiceRes res = new InvoiceRes();
		res.setSuccess("true");
		invoiceId= invoiceId.replaceAll("-", "/");
		List<Item> items= invoiceDao.getItems(invoiceId, year);
		List<Tax> taxSummary= invoiceDao.getTaxSummary(invoiceId, year);
		Invoice invoice =invoiceDao.getInvoiceSummaryById(year,invoiceId);
		res.setInvoice(getInvoiceVo(items,taxSummary,invoice));
		return res;
	}
	
	 @GET
	 @Path("/pdf")
	 @Produces("application/pdf")
	 public String getFileInPDFFormat() 
	 {
	        System.out.println("File requested is : ");
	        try {
	        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        File f = new File("C:\\Users\\SONY\\Desktop\\AlkebusinessCard\\apr-2014\\SevaUdyog-74.pdf");
	        final InputStream in = new FileInputStream(f);
	        

	        invoiceDao.storePDF(in,1,"Test1.pdf",(int)f.length());
	        System.out.println("completed");
	        }
	        catch(Exception ex) {
	        	ex.printStackTrace();
	        }
	        
	        
	        return "success";
	        
	 }
	 
	 
	 @GET
	 @Produces("application/json")
	 @Path("/purchase/{userid}/{year}")
	
	 public void getPurchaseSummary()  {
		 
		 
	 }
	 
	 @GET
	 @Path("/invoice/download/{user}/{year}/{invoiceId}")
	 @Produces("application/pdf")
	 public InputStream readFileInPDFFormat(@PathParam("user") String userId,@PathParam("year") int year, @PathParam("invoiceId") String invoiceId) 
	 {
		 
		    invoiceId = invoiceId.replaceAll("-", "/");
	        System.out.println("File requested is : ");
	        OutputStream outputStream =null;
	         InputStream in =null;
	        try {
	        
	        long clientId = invoiceDao.getClientId(userId);
	        in = invoiceDao.getBlobData(invoiceId, year,clientId);
	        
	       /* outputStream = 
                    new FileOutputStream(new File("f://new.pdf"));
 
			int read = 0;
			byte[] bytes = new byte[1024];
 
			 while((read = in.read(bytes)) != -1) {
				 outputStream.write(bytes, 0, read);
			 }
	        System.out.println("completed");
*/	        }
	        
	        catch(Exception ex) {
	        	ex.printStackTrace();
	        }
	        finally{
	        	
	        }
	        
	        return in;
	        
	 }
	 
	 private List<InvoiceVO> getInvoiceVo(List<Item> items,List<Tax> taxSummary,Invoice invoice) {
		
		List<InvoiceVO> invoiceVoList = new ArrayList<InvoiceVO>();
		
		int count=1;
		
		for(Item i:items) {
			InvoiceVO invo = new InvoiceVO();
			invo.setSrcNo(count);
			invo.setItemName(i.getItemName());
			invo.setGroupName("Items");
			invo.setQty(i.getQty());
			invo.setTotal(i.getTotal());
			invo.setRate(i.getRate());
			invoiceVoList.add(invo);
			count++;
		}
		
		for(Tax i:taxSummary) {
			InvoiceVO invo = new InvoiceVO();
			invo.setItemName(i.getTaxName());
			invo.setGroupName("Tax");
			
			invo.setTotal(i.getCost());
			invo.setRate(i.getCalc());
			invoiceVoList.add(invo);
		}
		
		InvoiceVO invo = new InvoiceVO();
		invo.setItemName("Grand Total");
		invo.setGroupName("Total");
		invo.setTotal(invoice.getInvoiceCost());
		invoiceVoList.add(invo);
		
		return invoiceVoList;
	}
	 
	 
	}
