package org.pstcl.portal.leave.mvc.model.sequenceUtil;

import org.pstcl.portal.leave.mvc.model.LeaveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class SequenceGenerationListener extends AbstractMongoEventListener<SequenceMarker> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public SequenceGenerationListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<SequenceMarker> event) {
    	
        if (null==event.getSource().getId() ) {
            event.getSource().setId(sequenceGenerator.generateSequence(SequenceMarker.class.getName()));
        }
    }


}