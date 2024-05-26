package com.komal.ProductService.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.komal.ProductService.dao.ProductDao;
import com.komal.ProductService.entity.ProductEntity;

import com.komal.ProductService.exception.ResourseAlreadyExistException;
import com.komal.ProductService.exception.ResourseNotExistException;
import com.komal.ProductService.exception.SomethingWentWrongException;
import com.komal.ProductService.model.CategoryModel;
import com.komal.ProductService.model.ProductModel;
import com.komal.ProductService.model.Product_Supplier_Category;
import com.komal.ProductService.model.SupplierModel;
import com.komal.ProductService.service.ProductService;
import com.komal.ProductService.utility.ObjectValidator;

@Service
public class ProductServiceImpl implements ProductService {
	
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ProductDao dao; 
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Autowired
	private ObjectValidator validator;
	
	
	Map<Integer, Map<String, String>> rowError= new HashMap<>();
	
	Map<String, Map<Integer, Map<String, String>>> badRecordError= new HashMap<>();
	
	
	Map<String, Object> finalMap= new HashMap<>();
	
	int totalrecords=0;
	
	
	List<Integer> rowNumList= new ArrayList<>();
	
	Map<String, String> validatationMap= new HashMap<>();
	
	@Override
	public boolean addproduct(ProductModel productmodel) {
		 ProductEntity productEntity = mapper.map(productmodel, ProductEntity.class);
		boolean isAdded = dao.addproduct(productEntity);
		return isAdded;
	}

	@Override
	public ProductModel getProductbyId(long productId) {
		 ProductEntity productentity = dao.getProductbyId(productId);
		if(productentity!=null)
		{
			ProductModel productmodel = mapper.map(productentity, ProductModel.class);
		
			return productmodel;
		}
		else {
			 throw new ResourseNotExistException("Supplier is not availble with : "+productId);
			}
		

	}

	@Override
	public boolean deleteProductById(long productId) {
		boolean IsDeleted = dao.deleteProductById(productId);
		// SupplierModel suppliermodel = mapper.map(IsDeleted, SupplierModel.class);
		return IsDeleted;
	}

	@Override
	public boolean updateproduct(ProductModel productmodel) {
		 ProductEntity productentity = mapper.map(productmodel, ProductEntity.class);
		boolean isUpdated = dao.updateproduct(productentity);
		
		if(isUpdated) {
			
			return true;
		}
		else {
			throw new ResourseNotExistException("Supplier iS not Available with "+productmodel.getProductId());
		}
		
		
	}



	@Override
	public List<ProductModel> getAllProduct() {
		List<ProductEntity> allProducts = dao.getAllProducts();
		List<ProductModel> pmodel=new ArrayList<>();
		if(allProducts!=null)
		{
			for (ProductEntity productentity : allProducts) {
				ProductModel productModel = mapper.map( productentity, ProductModel.class);
				pmodel.add(productModel);
			}
			
			return pmodel;
		}
		else{
			throw new ResourseNotExistException("resourse is not avilable");
		}
		
	}

	@Override
	public List<ProductModel> getProduuctByDesaOrder() {
		List<ProductEntity> plist= dao.getProduuctByDesaOrder();
		List<ProductModel> pmodel= new ArrayList<>();
		if(plist!=null) {
			for (ProductEntity productentity :  plist) {
				ProductModel productModel = mapper.map(productentity, ProductModel.class);
				pmodel.add(productModel);
				
			}
			return pmodel;
		
		}
		throw new ResourseNotExistException("resourse is not avilable");
	}

	@Override
	public List<ProductModel> sortProduct(String OrderType, String property) {
		 List<ProductEntity> sortProduct = dao.sortProduct(OrderType, property);
		 List<ProductModel> pmodel= new ArrayList<>();
		 
		 if(sortProduct!=null) {
			 for (ProductEntity productentity : sortProduct) {
						ProductModel productModel = mapper.map(productentity, ProductModel.class);
						pmodel.add(productModel);
			}
		 }
		 else {
			 throw new ResourseNotExistException("resourse is not avilable");
		 }
		 
		return pmodel;
	}

	

	@Override
	public double gettingMaxPriceProduct() {
	double maxPriceProduct = dao.gettingMaxPriceProduct();
	if(maxPriceProduct>0)
	{
		return maxPriceProduct;
	}
	else {
		throw new ResourseNotExistException("resourse is not avilable");
	}
		
	}
	
	
	@Override
	public List<ProductModel> getMaxPriceProduct() {
		List<ProductEntity> maxPriceProduct = dao.getMaxPriceProduct();
		List<ProductModel> pmodel= new ArrayList<>();
		
			for (ProductEntity productentity : maxPriceProduct) {
				ProductModel productmodel = mapper.map(productentity, ProductModel.class);
				pmodel.add(productmodel);
			}
		
			return pmodel;
		}

	@Override
	public ProductModel getproductByName(String productName) {
		
		
			ProductEntity productEntity = dao.getproductByName(productName);
		if(productEntity!=null) {
			ProductModel map = mapper.map(productEntity, ProductModel.class);
			return map;
		}
		else {
			throw new ResourseNotExistException("product not available");
		}
	
	}
	
	@Override
	public ProductModel getProductsByName(String productName) {
		
		ProductEntity dbProduct = dao.getProductsByName(productName);
		if (dbProduct != null) {
			return mapper.map(dao.getProductsByName(productName), ProductModel.class);
		} else {
			return null;
		}
	}

	@Override
	public List<ProductModel> getAllProducts(double lowPrice, double highPrice) {
		List<ProductEntity> allProducts = dao.getAllProducts(lowPrice, highPrice);
		List<ProductModel> pmodel= new ArrayList<>();
		if(!allProducts.isEmpty())
		{
			for (ProductEntity productentity : allProducts) {
			ProductModel map = mapper.map(productentity, ProductModel.class);
			
		
				pmodel.add(map);
			}
			return pmodel;
		}
		
		else {
			throw new ResourseNotExistException("product is not avilable");
		}
	}

	@Override
	public List<ProductModel> getproductStartWith(String expression) {
		List<ProductEntity> getproductStartWith = dao.getproductStartWith(expression);
		List<ProductModel> pmodel= new ArrayList<>();
		
		if(getproductStartWith!=null) {
			for (ProductEntity productEntity : getproductStartWith) {
				ProductModel map = mapper.map(productEntity, ProductModel.class);
				pmodel.add(map);
			}
		}
		else {
			 throw new ResourseNotExistException("product is not avilable");
		}
		return pmodel;
	}

	@Override
	public double productPriceAverage() {
		double productPrice= dao.productPriceAverage();
		
		return productPrice;
	}

	@Override
	public double countOfTotalProduct() {
		double countOfTotalProduct = dao.countOfTotalProduct();
		
		if(countOfTotalProduct>0)
		{
		return countOfTotalProduct;
		}
		else
		{
			throw new ResourseNotExistException("count is wrong");
		}
	}

	@Override
	public ProductModel getproductByProductQuantity(int productQuantity) {
	ProductEntity city = dao.getproductByProductQuantity(productQuantity);
	
	if(city!=null) {
		ProductModel map = mapper.map(city, ProductModel.class);
		return map;
	}
	else {
		throw new ResourseNotExistException("this product quantity not exist");
	}
	}

	@Override
	public Map<String, Object> uploadSheet(MultipartFile file) {
		int isaddedcount=0;
		int alreadyexistcount=0;
		int issuecounter=0;
		try {
			String path="src/main/resources";
			//String path="uploaded";
			String filename=file.getOriginalFilename();
			FileOutputStream fos= new FileOutputStream(path+File.separator+filename);
			byte[] data = file.getBytes();
			fos.write(data);
			
		List<ProductModel> readExcel = readExcel(path+File.separator+filename);
		for (ProductModel productModel : readExcel) {
			ProductEntity productentity = mapper.map(productModel, ProductEntity.class); 
			System.out.println(productentity);
			
			try {
				 boolean addproduct = dao.addproduct(productentity);
				if(addproduct)
				{
					
					isaddedcount++;
				}
			} catch (SomethingWentWrongException e) {
				issuecounter++;
			} catch(ResourseAlreadyExistException e){
				 alreadyexistcount++;
			}
		}
		
		finalMap.put("Total Records in sheet", totalrecords);
		finalMap.put("uploaded Record in db	", isaddedcount );
		finalMap.put("Total exist Record in db", rowNumList.size());
		finalMap.put("Row Num, exist record in db", rowNumList);
		finalMap.put("Total Excluded record count", totalrecords-isaddedcount);
		finalMap.put("Bad Record Row Number", rowError);
		
		}catch (Exception e) {
			e.printStackTrace();
		}	
		
	return finalMap;
	}

	private List<ProductModel> readExcel(String filePath) {
		List<ProductModel> list= new ArrayList<>();
		try {
			
			//Creates a FileInputStream to read the Excel file from the specified filePath.
		FileInputStream fis= new FileInputStream(filePath);
		
		Workbook workbook= WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheetAt(0);
		 totalrecords = sheet.getLastRowNum();
		
		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext()) {
			Row row = (Row) rows.next();
			int rowNum = row.getRowNum();
			if(rowNum==0) { // to skip the header part
				continue;
			}
			
			//for every row will create product model
			
			
			ProductModel productmodel = new ProductModel();
			String timeStamp= new SimpleDateFormat("yyMMddHHmmss").format(Calendar.getInstance().getTime());
			productmodel.setProductId(Long.parseLong(timeStamp)+rowNum);
		
			Iterator<Cell> cells = row.cellIterator();
			
			
			while (cells.hasNext()) {
				Cell cell =  cells.next();
				//get data
				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				case 0: {
					String productName = cell.getStringCellValue();
					productmodel.setProductName(productName);
					break;
				}
				case 1:{
					double supplierId = cell.getNumericCellValue();
					
					
					productmodel.setSupplierId((long)cell.getNumericCellValue());
					break;
				}
				case 2:{
					double categoryId = cell.getNumericCellValue();
				
					productmodel.setCategoryId((long)cell.getNumericCellValue());
					break;
				}
				case 3:{
					double productQuantity = cell.getNumericCellValue();
					productmodel.setProductQuantity((int)productQuantity);
					break;
				}
				case 4:{
					double productPrice = cell.getNumericCellValue();
					productmodel.setProductPrice(productPrice);
					break;
				}
				case 5:{
					double deliveryCharges = cell.getNumericCellValue();
					productmodel.setDeliveryCharges((int)deliveryCharges);
					break;
				}
				
				}
				
//				CellType cellType = cell.getCellType();
//				
//				
//				if(cellType==CellType.STRING) {
//					System.out.println(cell.getStringCellValue());
//					
//				}else {
//					System.out.println(cell.getNumericCellValue());
//					
//				}
				
			}
			
		
		 validatationMap = validator.validateProduct(productmodel);
		
			if(!validatationMap.isEmpty()) {
			
					//if validation map is not empty
				rowError.put(rowNum+1, validatationMap);
			}else {
					ProductModel dbproduct = getProductsByName(productmodel.getProductName());
					if(dbproduct!=null) {
						rowNumList.add(rowNum+1);
						
					}
					else {
						list.add(productmodel);
					}
			     }
			
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		return list;
		
		
		
		
	}

	@Override
	public Product_Supplier_Category getProductwithSCbyPId(long productId) {
		ProductModel productModel = getProductbyId(productId);
		Product_Supplier_Category psc=null;
		if(productModel!=null) {
			psc=new Product_Supplier_Category();
			psc.setProductModel(productModel);
			
			try {
				SupplierModel supplierModel=restTemplate.getForObject("http://SUPPLIERSERVICE/supplier/get-supplier/"+productModel.getSupplierId(), SupplierModel.class);
				if(supplierModel.getSupplierId()<=0) {
					psc.setSupplierModel(null);
				}else {
				
				
				psc.setSupplierModel(supplierModel);
				}
				
			} catch (ResourceAccessException e) {
				psc.setSupplierModel(null);
			}
			
			
			try {
				CategoryModel categoryModel=restTemplate.getForObject("http://CATEGORYSERVICE/category/get-category/"+productModel.getCategoryId(), CategoryModel.class);
				if(categoryModel.getCategoryId()<=0)
				{
					
					psc.setCategoryModel(null);
				}
				else {
					psc.setCategoryModel(categoryModel);
				}
				
			} catch (ResourceAccessException e) {
				psc.setCategoryModel(null);
			}
			
		}else {
			
		}
	
		return psc;
		
	}

	


}
