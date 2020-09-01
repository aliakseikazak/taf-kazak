package by.kazak.taf.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Dashboard {

    @NonNull
    private String name;
    private String description;
    private boolean share;
}
