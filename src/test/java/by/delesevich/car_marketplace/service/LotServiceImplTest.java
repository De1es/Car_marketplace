package by.delesevich.car_marketplace.service;

import by.delesevich.car_marketplace.dto.LotDtoForAdmin;
import by.delesevich.car_marketplace.dto.LotDtoForUsers;
import by.delesevich.car_marketplace.entity.lot.Lot;
import by.delesevich.car_marketplace.entity.lot.LotPage;
import by.delesevich.car_marketplace.entity.user.User;
import by.delesevich.car_marketplace.entity.vehicle.Vehicle;
import by.delesevich.car_marketplace.repository.LotRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LotServiceImplTest {

  @MockBean
  LotRepository mockRepository;

  @Autowired
  LotService lotService;

  static Lot lot1;
  static Lot lot2;
  static Lot lot3;
  static Lot lot4;
  static LotPage lotPage;
  static Page<Lot> pageOfLot1;
  static Page<Lot> pageOfLot2;
  static Page<LotDtoForUsers> pageOfLotDtoForUsers;
  static Page<LotDtoForAdmin> pageOfLotDtoForAdmin;
  static Pageable pageable;

  @BeforeAll
  static void initTestData() {
    lot1 = new Lot(1L, new User(), new Vehicle(), "title1",
        "description1", new Timestamp(1000L), 1000, null);
    lot2 = new Lot(2L, new User(), new Vehicle(), "title2",
        "description2", new Timestamp(2000L), 2000, null);
    lot3 = new Lot(3L, new User(), new Vehicle(), "title3",
        "description3", new Timestamp(3000L), 3000, null);
    lot4 = new Lot(4L, new User(), new Vehicle(), "title4",
        "description4", new Timestamp(4000L), 4000, new Timestamp(4000L));
    lotPage = new LotPage();
    List<Lot> listOfLot1 = new ArrayList<>();
    listOfLot1.add(lot1);
    listOfLot1.add(lot2);
    listOfLot1.add(lot3);
    pageOfLot1 = new PageImpl<>(listOfLot1);
    List<Lot> listOfLot2 = new ArrayList<>();
    listOfLot2.add(lot1);
    listOfLot2.add(lot2);
    listOfLot2.add(lot3);
    listOfLot2.add(lot4);
    pageOfLot2 = new PageImpl<>(listOfLot2);
    Sort sort = Sort.by(lotPage.getSortDirection(), lotPage.getSortBy().toString());
    pageable = PageRequest.of(lotPage.getPageNumber(), lotPage.getPageSize(), sort);
    List<LotDtoForUsers> listForUsers =
        pageOfLot1.getContent()
            .stream()
            .map(LotDtoForUsers::new)
            .collect(Collectors.toList());
    pageOfLotDtoForUsers = new PageImpl<>(listForUsers, pageable, pageOfLot2.getTotalElements());

    List<LotDtoForAdmin> listForAdmin =
        pageOfLot2.getContent()
            .stream()
            .map(LotDtoForAdmin::new)
            .collect(Collectors.toList());
    pageOfLotDtoForAdmin = new PageImpl<>(listForAdmin, pageable, pageOfLot2.getTotalElements());

  }


  @Test
  void findById() {
    when(mockRepository.findById(2L)).thenReturn(java.util.Optional.of(lot1));
    when(mockRepository.findById(0L)).thenReturn(java.util.Optional.empty());

    assertAll(
        () -> assertEquals(lot1, lotService.findById(2L)),
        () -> assertThrows(IllegalArgumentException.class, () -> lotService.findById(0L))
    );
    verify(mockRepository).findById(2L);
    verify(mockRepository).findById(0L);
  }

  @Test
  void findAllNotDeleted() {
    when(mockRepository.findAllNotDeleted(pageable)).thenReturn(pageOfLot1);

    assertEquals(pageOfLotDtoForUsers, lotService.findAllNotDeleted(lotPage));
    verify(mockRepository).findAllNotDeleted(pageable);
  }

  @Test
  void findAll() {
    when(mockRepository.findAll(pageable)).thenReturn(pageOfLot2);

    assertEquals(pageOfLotDtoForAdmin, lotService.findAll(lotPage));
    verify(mockRepository).findAll(pageable);
  }

  @Test
  void saveOrUpdateLot() {
    when(mockRepository.save(lot1)).thenReturn(lot1);

    assertEquals(lot1, lotService.saveOrUpdateLot(lot1));
    verify(mockRepository).save(lot1);

  }

  @Test
  void softDelete() {
    when(mockRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(lot1));
    when(mockRepository.findById(0L)).thenReturn(java.util.Optional.empty());


    assertAll(
        () -> assertEquals(1L, lotService.softDelete(1L)),
        () -> assertThrows(IllegalArgumentException.class, () -> lotService.softDelete(0L))
    );
    verify(mockRepository).softDelete(1L);

  }
}