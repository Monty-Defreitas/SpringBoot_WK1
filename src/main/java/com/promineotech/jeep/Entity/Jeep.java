package com.promineotech.jeep.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Comparator;

     @Data
     @Builder
     @NoArgsConstructor
     @AllArgsConstructor
    public class Jeep implements Comparable<Jeep> {
         @JsonIgnore
        private Long modelPK;

        private JeepModel modelId;
        private String trimLevel;
        private int numDoors;
        private int wheelSize;
        private BigDecimal basePrice;

        @Override
        public int compareTo(Jeep that) {
            return Comparator.comparing( Jeep::getModelId)
                    .thenComparing(Jeep::getTrimLevel)
                    .thenComparing(Jeep::getNumDoors)
                    .compare(this, that);
        }
    }


