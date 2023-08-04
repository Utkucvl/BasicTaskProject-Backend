package utku.basictask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import utku.basictask.dto.AuthDto;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);
    @Mapping(target="refreshToken" , source = "refreshToken")
    AuthDto loadBlanksForLogin(int userId,String accessToken ,String message,String refreshToken);
    @Mapping(target = "accessToken",ignore = true)
    AuthDto loadBlanksForRegister(int userId , String message);

    @Mapping(target = "userId" , ignore = true)
    @Mapping(target = "accessToken" , ignore = true)
    AuthDto loadBlanksForNonSuccessfullRegister( String message);

    AuthDto loadBlanksForRefresh(String message,String accessToken,int userId,String refreshToken);
    @Mapping(target = "userId" , ignore = true)
    @Mapping(target = "accessToken" , ignore = true)
    @Mapping(target = "refreshToken" , ignore = true)
    AuthDto loadBlanksForNonRefresh(String message);
}
