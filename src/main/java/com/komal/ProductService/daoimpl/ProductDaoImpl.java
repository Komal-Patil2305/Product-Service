package com.komal.ProductService.daoimpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.imageio.ImageTranscoder;
import javax.persistence.RollbackException;

import org.apache.poi.hssf.record.chart.BeginRecord;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.komal.ProductService.dao.ProductDao;

import com.komal.ProductService.entity.ProductEntity;

import com.komal.ProductService.exception.ResourseAlreadyExistException;
import com.komal.ProductService.exception.ResourseNotExistException;
import com.komal.ProductService.exception.SomethingWentWrongException;

@Repository
public class ProductDaoImpl implements ProductDao{

	@Autowired
	private SessionFactory sf;
	
	
	@Override
	public boolean addproduct(ProductEntity productentity) {
		boolean isAdded=false;
		Transaction t=null;
		try {
			
			Session session=sf.openSession();
		
			System.out.println("heello");
		//	ProductEntity entity = getProductByName(productentity.getProductName());
				//	System.out.println(entity);
			
	//	if(entity==null) {
		
				System.out.println("hi");
		
			
				 session.save(productentity);
				 t=session.beginTransaction();
				 t.commit();
				 System.out.println(productentity);
				
				
				
				isAdded=true;
		//	}
		}
	
	catch(Exception e){
			e.printStackTrace();
		throw new SomethingWentWrongException("something went wrong during adding product");
		}
		
		return isAdded;
	
	}
	
	




	@Override
	public ProductEntity getProductbyId(long productId) {
		ProductEntity productentity = null;
		
		try {
			Session session=sf.openSession();
			 productentity= session.get( ProductEntity.class, productId);
			
			
		} catch (HibernateException he) {
			he.printStackTrace();
			
		throw new  SomethingWentWrongException("connection is not established");
		}
		
		return productentity;
	}


	@Override
	public boolean deleteProductById(long productId) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean updateproduct(ProductEntity productentity) {
		boolean isUpadated=false;
		try {
			ProductEntity dbentity= getProductbyId(productentity.getProductId());
			
			
			if(dbentity!=null) {
				Session session=sf.openSession();
				Transaction t= session.beginTransaction();
				session.update(productentity);
				t.commit();
				isUpadated=true;
			}
			
			
		} catch (Exception e) {
			
			throw new SomethingWentWrongException("something went wrong during updating product");
		}
		return isUpadated;
	}


	


	@SuppressWarnings("deprecation")
	@Override
	public List<ProductEntity> getAllProducts() {
		
		List<ProductEntity> pentity=null;
		try {
			Session session=sf.openSession();
			Criteria criteria = session.createCriteria(ProductEntity.class);
			pentity = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pentity;
	}


	@SuppressWarnings("deprecation")
	@Override
	public List<ProductEntity> getProduuctByDesaOrder() {
		List<ProductEntity>  list=null;
		try {
			Session s=sf.openSession();
			Criteria criteria = s.createCriteria(ProductEntity.class);
		 criteria.addOrder(Order.desc("productPrice"));
			list=criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
	}


	@Override
	public List<ProductEntity> sortProduct(String OrderType, String property) {
		List<ProductEntity>  list=null;
		try {
			Session s=sf.openSession();
			Criteria criteria = s.createCriteria(ProductEntity.class);
			if(OrderType.equalsIgnoreCase("desc")) {
				criteria.addOrder(Order.desc(property));
				
			}
			else {
				criteria.addOrder(Order.asc(property));
				
			}
			list=criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("something went wrong during sorting product");
		}
		return list;
	}




	@SuppressWarnings("deprecation")
	@Override
	public double gettingMaxPriceProduct() {
		double maxprice=0;
		try {
			Session s= sf.openSession();
			Criteria criteria = s.createCriteria(ProductEntity.class);
			 criteria.setProjection(Projections.max("productPrice"));
			 List list = criteria.list();
			 if(!list.isEmpty())
			 {
			 maxprice= (double) list.get(0);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxprice;
	}




	@Override
	public List<ProductEntity> getMaxPriceProduct() {
		double maxPriceProduct = gettingMaxPriceProduct();
		List<ProductEntity> list=null;
		if(maxPriceProduct>0) {
			Session s= sf.openSession();
			Criteria criteria = s.createCriteria(ProductEntity.class);
			
			criteria.add(Restrictions.eq("productPrice", maxPriceProduct));
		 list = criteria.list();
		}
		else {
			throw new ResourseNotExistException("product not exist"); 
		}
		
		
		return list;
	}

								
	@Override
	public ProductEntity getproductByName(String productName) {
	//	public ProductEntity getProductByName(String productName) {
		List<ProductEntity> productList=null;
	
			try {
				//if(productName!=null)
				//{
				Session s=sf.openSession();
//				Query<ProductEntity> query = s.createQuery("From ProductEntity Where productName= :name");
//				query.setParameter("name", productName);
				System.out.println(productName);
				Criteria c= s.createCriteria(ProductEntity.class);
			 c.add(Restrictions.eq("productName", productName));
			 productList = c.list();
			System.out.println(productList);
			if(!productList.isEmpty()) {
				return productList.get(0);
				
			}
			else {
				 throw new ResourseNotExistException("Product not found with name: " + productName);
			}
				
			//	}
			//	else {
//				
			//	}
		} catch (Exception e) {
			
			throw new SomethingWentWrongException("something went wrong during getting product");
		}	
		
	}


	@SuppressWarnings("deprecation")
	@Override
	public List<ProductEntity> getAllProducts(double lowPrice,double highprice) {
		List<ProductEntity> list=null;
		try {
			Session s= sf.openSession();
			Criteria criteria = s.createCriteria(ProductEntity.class);
			criteria.add(Restrictions.between("productPrice", lowPrice, highprice));
			 list = criteria.list();
		} catch (Exception e) {
			throw new SomethingWentWrongException("something went wrong");
		}
		return list;
	}


	@SuppressWarnings("deprecation")
	@Override
	public List<ProductEntity> getproductStartWith(String expression) {
		List<ProductEntity> list=null;
		try {
			
			Session s = sf.openSession();
			Criteria c = s.createCriteria(ProductEntity.class);
			c.add(Restrictions.like("productName", expression + "%"));
			 list = c.list();
			
		} catch (Exception e) {
			throw new SomethingWentWrongException("something went wrong");
		}
		return list;
	}


	@SuppressWarnings("deprecation")
	@Override
	public double productPriceAverage() {
		
		double avgprice=0;
		List list=null;
		try {
			Session s = sf.openSession();
			Criteria c = s.createCriteria(ProductEntity.class);
			c.setProjection(Projections.avg("productPrice"));
			 list = c.list();
			 
			 if(!list.isEmpty()) {
				 avgprice = (double) list.get(0);
			 }
			 
		} catch (Exception e) {
			throw new SomethingWentWrongException("something went wrong");
		}
		return avgprice;
	}


	@Override
	public double countOfTotalProduct() {
		double countOfProduct=0;
		List list=null;
		try {
			Session s = sf.openSession();
			Criteria c = s.createCriteria(ProductEntity.class);
			c.setProjection(Projections.rowCount());
			
		 Long result = (Long) c.uniqueResult();
		 countOfProduct=result!=null ? result.doubleValue() : 0.0;
		
			 s.close();
			 
		} catch (Exception e) {
			throw new SomethingWentWrongException("something went wrong");
		}
		return countOfProduct;
	}


	@Override
	public ProductEntity getproductByProductQuantityall(int productQuantity) {
		 
		ProductEntity product=null;
		ProductEntity uniqueResult=null;
		//List<Object[]> uniqueResult=null;
		try {
			
			Session s = sf.openSession();
			Transaction t = s.beginTransaction();
			 uniqueResult = 	s.createQuery(" FROM ProductEntity   where productQuantity = :pquantity", ProductEntity.class).setParameter("pquantity", productQuantity).uniqueResult();
//			   uniqueResult = (List<Object[]>) s.createQuery("select p.productName, p.productPrice FROM ProductEntity p  where productQuantity = :pquantity", ProductEntity.class).setParameter("pquantity", productQuantity).uniqueResult();
//		
//			   if (uniqueResult != null && !uniqueResult.isEmpty()) {
//				   Object[] result = uniqueResult.get(0); // Assuming only one result is expected
//		            String productName = (String) result[0];
//		            Double productPrice = (Double) result[1];
//
//		            product = new ProductEntity();
//		            product.setProductName(productName);
//		            product.setProductPrice(productPrice); // Convert Double to BigDecimal if needed
//
		           t.commit();
//		          
//			}else {
//				throw new ResourseNotExistException("product Not Available");
//			}
			   
			
			
		} catch (Exception e) {
			throw new SomethingWentWrongException("something went wrong during getting product quantity");
		}
		return uniqueResult;
		
	}

	@Override
	public ProductEntity getproductByProductQuantity(int productQuantity) {
	    ProductEntity product = null;
	    Transaction transaction = null;

	    try (Session session = sf.openSession()) {
	        transaction = session.beginTransaction();

	        // Query to retrieve productName and productPrice based on productQuantity
	        Object[] result = (Object[]) session.createQuery(
	                "SELECT p.productName, p.productPrice FROM ProductEntity p WHERE p.productQuantity = :pquantity")
	                .setParameter("pquantity", productQuantity)
	                .uniqueResult();

	        if (result != null) {
	            String productName = (String) result[0];
	            Double productPrice = (Double) result[1];

	            // Create a new ProductEntity and set properties
	            product = new ProductEntity();
	            product.setProductName(productName);
	            product.setProductPrice(productPrice); // Convert Double to BigDecimal if needed

	            // Commit the transaction if successful
	            transaction.commit();
	        } else {
	            // Rollback the transaction if no product found
	            transaction.rollback();
	            throw new ResourseNotExistException("Product not available for quantity: " + productQuantity);
	        }

	    } catch (ResourseNotExistException e) {
	        throw e; // Re-throw specific exception for higher-level handling
	    } catch (Exception e) {
	        // Rollback the transaction in case of any exception
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        throw new SomethingWentWrongException("Error while fetching product by quantity");
	    }

	    return product;
	}


	@Override
	public ProductEntity getProductsByName(String productName) {
		
		// select * from product where product_name=? // Restrictions

				// HQL: from ProductEntity where productName= :parametername (parametername :
				// pname)
				List<ProductEntity> list = null;
				ProductEntity productEntity = null;
				try (Session session = sf.openSession()) {

					Query<ProductEntity> query = session.createQuery("FROM ProductEntity WHERE productName= :name");

					query.setParameter("name", productName);

					list = query.list();

					if (!list.isEmpty()) {
						productEntity = list.get(0);
					} else {
						return null;
					}

				}

				catch (Exception e) {
					e.printStackTrace();
				}

				return productEntity;
			}

	}






	

	

 




