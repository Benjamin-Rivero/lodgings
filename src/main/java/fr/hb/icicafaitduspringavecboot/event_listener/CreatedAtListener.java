package fr.hb.icicafaitduspringavecboot.event_listener;

import fr.hb.icicafaitduspringavecboot.entity.interfaces.CreatedAtInterface;
import lombok.AllArgsConstructor;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
        if (o instanceof CreatedAtInterface cai) {
            if (cai.getCreatedAt() == null) {
                cai.setCreatedAt(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }
}
