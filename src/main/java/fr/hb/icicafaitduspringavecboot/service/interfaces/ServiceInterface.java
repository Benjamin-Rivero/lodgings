package fr.hb.icicafaitduspringavecboot.service.interfaces;

public interface ServiceInterface<T,L,C,U> {

    T create(C object);

    T update(U object, L id);

    void delete(T object);

    T findById(L id);

}
