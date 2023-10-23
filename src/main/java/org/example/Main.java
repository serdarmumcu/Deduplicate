package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Input
        Tag dog1 = new Tag(1, "dog");
        TagLink dog1Link1 = new TagLink(11, 1, 100, TargetType.NODE);
        TagLink dog1Link2 = new TagLink(12, 1, 101, TargetType.SECTION);
        TagLink dog1Link3 = new TagLink(13, 1, 102, TargetType.FORM_ELEMENT);
        Tag dog2 = new Tag(2, "dog");
        TagLink dog2Link1 = new TagLink(21, 2, 100, TargetType.NODE);
        TagLink dog2Link2 = new TagLink(22, 2, 101, TargetType.SECTION);
        TagLink dog2Link3 = new TagLink(23, 2, 202, TargetType.FORM_ELEMENT);


        // Output
        // dog2 must be deleted
        // dog2Link1 must be deleted
        // dog2Link2 must be deleted
        // dog2Link3 must be updated to tag1

        DeduplicateTagsAndLinks deduplicateTagsAndLinks = new DeduplicateTagsAndLinks();

        Map<Tag,List<TagLink>> result = deduplicateTagsAndLinks.deduplicate(
                List.of(dog1, dog2),
                List.of(dog1Link1, dog1Link2, dog1Link3, dog2Link1, dog2Link2, dog2Link3));

        for (Map.Entry<Tag, List<TagLink>> entry : result.entrySet()) {
            System.out.println("Tag ID: " + entry.getKey().getId() + ", Tag Name: " + entry.getKey().getName());
            for (TagLink link : entry.getValue()) {
                System.out.println("\tTagLink -- ID: " + link.getId() + ", TagID: " + link.getTagId() +
                        ", TargetID: " + link.getTargetId() + ", TargetType: " + link.getTargetType());
            }
        }
    }
}
