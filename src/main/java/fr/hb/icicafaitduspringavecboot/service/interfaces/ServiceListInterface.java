package fr.hb.icicafaitduspringavecboot.service.interfaces;

import java.util.List;

public interface ServiceListInterface<T,L> extends ServiceInterface<T,L> {

    List<T> list();

}
