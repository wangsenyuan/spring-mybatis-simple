package foo.bar.dao;

import foo.bar.model.Supplier;

/**
 * Created by senyuanwang on 14/11/10.
 */
public interface ISupplierDAO {
    public int insertSupplier(Supplier supplier);

    Supplier findSuppiler(long id);
}
