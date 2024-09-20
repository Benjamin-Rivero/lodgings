package fr.hb.icicafaitduspringavecboot.service.interfaces;

public interface ServiceInterface<T,L> {

    T create(T object);

    T update(T object, L id);

    void delete(T object);

    T findById(L id);

}
