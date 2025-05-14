package edu.ute.PhamThanhHieu_WebToDoList.utils;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Named;

import edu.ute.PhamThanhHieu_WebToDoList.model.Tag;

public class TagMapperHelper {
    @Named("mapTagsToNames")
    public static Set<String> mapTagsToNames(Set<Tag> tags) {
        if (tags == null) return Collections.emptySet();
        return tags.stream().map(Tag::getName).collect(Collectors.toSet());
    }
}