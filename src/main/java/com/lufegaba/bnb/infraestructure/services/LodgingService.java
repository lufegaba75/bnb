package com.lufegaba.bnb.infraestructure.services;

import com.lufegaba.bnb.domain.*;
import com.lufegaba.bnb.domain.repositories.*;
import com.lufegaba.bnb.infraestructure.exceptions.IdNotFoundException;
import com.lufegaba.bnb.infraestructure.exceptions.NoHirerException;
import com.lufegaba.bnb.infraestructure.utilities.Tables;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class LodgingService {

    private final LodgingRepository lodgingRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final AddressRepository addressRepository;
    private final MediaRepository mediaRepository;
    private final ExtrasRepository extrasRepository;

    public User userById (Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(Tables.users.name()));
    }

    public Lodging createLodging (Lodging lodging) {
        var now = LocalDateTime.now();
        lodging.setCreatedAt(now);
        lodging.setUpdatedAt(now);
        return lodgingRepository.save(lodging);
    }

    public Lodging assignToUser (Integer id, Lodging lodging) {
        var owner = userById(id);
        if (!(owner.isHirer(owner))) {
            lodgingRepository.deleteById(lodging.getId());
            throw new NoHirerException();
        }
        lodging.setOwner(owner);
        owner.setUpdatedAt(LocalDateTime.now());
        return lodgingRepository.save(lodging);
    }

    public List<Lodging> showUserLodgings (Integer id) {
        var owner = userById(id);
        return lodgingRepository.getLodgingsByOwner(owner);
    }

    public Lodging getLodgingById (Integer id) {
        return lodgingRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(Tables.lodging.name()));
    }

    public Lodging addCoordinates (Integer id, Location location) {
        locationRepository.save(location);
        var lodgingToUpdate = getLodgingById(id);
        lodgingToUpdate.setLocation(location);
        return lodgingToUpdate;
    }

    public Lodging addAddress (Integer id, Address address) {
        var lodgingToUpdate = getLodgingById(id);
        addressRepository.save(address);
        lodgingToUpdate.setAddress(address);
        return lodgingToUpdate;
    }

    public Lodging addMedia (Integer id, Media media) {
        var lodgingToUpdate = getLodgingById(id);
        var mediaSaved = mediaRepository.save(media);
        var medias = lodgingToUpdate.getMedias();
        medias.add(mediaSaved);
        lodgingToUpdate.setMedias(medias);
        return lodgingToUpdate;
    }

    public Lodging addExtras (Integer id, Extras extras) {
        var lodgingToUpdate = getLodgingById(id);
        var extrasSaved = extrasRepository.save(extras);
        lodgingToUpdate.setExtras(extrasSaved);
        return lodgingToUpdate;
    }

    public void deleteLodgingById (Integer id) {
        lodgingRepository.deleteById(id);
    }

    public Lodging updateLodging (Integer id, Lodging lodging) {
        var lodgingToUpdate = getLodgingById(id);
        if (lodging.getName() != null) lodgingToUpdate.setName(lodging.getName());
        if (lodging.getGuestCapacity() != null) lodgingToUpdate.setGuestCapacity(lodging.getGuestCapacity());
        if (lodging.getCategory() != null) lodgingToUpdate.setCategory(lodging.getCategory());
        if (lodging.getPriceByNight() != null) lodgingToUpdate.setPriceByNight(lodging.getPriceByNight());
        if (lodging.getDescription() != null) lodgingToUpdate.setDescription(lodging.getDescription());
        if (lodging.getCheckInHour() != null) lodgingToUpdate.setCheckInHour(lodging.getCheckInHour());
        if (lodging.getCheckOutHour() != null) lodgingToUpdate.setCheckOutHour(lodging.getCheckOutHour());
        if (lodging.getAddress() != null) addAddress(id, lodging.getAddress());
        if (lodging.getLocation() != null) addCoordinates(id, lodging.getLocation());
        if (lodging.getExtras() != null) addExtras(id, lodging.getExtras());
        if (lodging.getMedias() != null) {
            addMedia(id, lodging.getMedias().stream().findFirst()
                    .orElseThrow(() -> new IdNotFoundException(Tables.media.name())));
        }
        lodgingToUpdate.setUpdatedAt(LocalDateTime.now());
        return lodgingToUpdate;
    }

}
