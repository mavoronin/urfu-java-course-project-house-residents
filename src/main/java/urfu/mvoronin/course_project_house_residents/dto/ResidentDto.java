package urfu.mvoronin.course_project_house_residents.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import urfu.mvoronin.course_project_house_residents.entity.Resident;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResidentDto {

    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String phoneNumber;

    @NotNull
    private Integer flat;

    public static ResidentDto FromEntity(Resident resident) {
        var residentDto = new ResidentDto();
        residentDto.setFlat(resident.getFlat());
        residentDto.setId(resident.getId());
        residentDto.setName(resident.getName());
        residentDto.setSurname(resident.getSurname());
        residentDto.setPhoneNumber(resident.getPhoneNumber());

        return residentDto;
    }

    public Resident AsEntity() {
        var resident = new Resident();
        resident.setFlat(flat);
        resident.setId(id);
        resident.setSurname(surname);
        resident.setName(name);
        resident.setPhoneNumber(phoneNumber);

        return resident;
    }
}
