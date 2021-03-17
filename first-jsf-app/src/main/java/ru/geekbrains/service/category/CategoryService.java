package ru.geekbrains.service.category;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoryService {

    List<CategoryRepr> findAll();

    CategoryRepr findById(Long id);

    Long countAll();

    void insert(CategoryRepr category);

    void saveOrUpdate(CategoryRepr category);

    void deleteById(Long id);
}
