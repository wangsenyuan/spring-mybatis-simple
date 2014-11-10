package foo.bar;

import foo.bar.dao.ISupplierDAO;
import foo.bar.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by senyuanwang on 14/11/10.
 */
@Component
public class SupplierService {

    @Autowired
    private ISupplierDAO supplierDAO;

    private TransactionTemplate transactionTemplate;

    @Autowired
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    public void insertSupplierInTx(final Supplier supplier) {
        try {
            this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    supplierDAO.insertSupplier(supplier);
                    throw new RuntimeException("fail");
                }
            });
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertSupplierNoTx(Supplier supplier) {
        this.supplierDAO.insertSupplier(supplier);
    }

    public Supplier findSupplier(long id) {
        return this.supplierDAO.findSuppiler(id);
    }
}
