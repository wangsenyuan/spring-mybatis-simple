package foo.bar;

import foo.bar.model.Supplier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        SupplierService supplierService = context.getBean(SupplierService.class);
        testInsertInTx(supplierService);
        testInsertNoTx(supplierService);
    }

    private static void testInsertNoTx(SupplierService supplierService) {
        Supplier mock = new Supplier();
        mock.setId(2L);
        mock.setName("mock");
        mock.setState("ZJ");
        mock.setStreet("SD");
        mock.setCity("HZ");
        mock.setZip("310030");
        supplierService.insertSupplierNoTx(mock);

        Supplier found = supplierService.findSupplier(2L);
        System.out.println(found);
    }

    private static void testInsertInTx(SupplierService supplierService) {
        Supplier mock = new Supplier();
        mock.setId(1L);
        mock.setName("mock");
        mock.setState("ZJ");
        mock.setStreet("SD");
        mock.setCity("HZ");
        mock.setZip("310030");
        supplierService.insertSupplierInTx(mock);

        Supplier found = supplierService.findSupplier(1L);
        System.out.println(found);
    }
}
