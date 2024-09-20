package fr.hb.icicafaitduspringavecboot.event_listener;

import lombok.AllArgsConstructor;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreatedAtListener implements PreInsertEventListener {
    @Override
    public boolean onPreInsert(PreInsertEvent preInsertEvent) {
        try {
            return hasCreatedAt(preInsertEvent.getEntity());
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private boolean hasCreatedAt(Object o) throws NoSuchFieldException {
        try {
            o.getClass().getDeclaredField("createdAt");
            return true;
        } catch (NoSuchFieldException e){
            return false;
        }
    }
}
