package org.example;


import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter .api.Assertions.*;


class DeduplicateTagsAndLinksTest {


    @Test
    void deduplicate() {
        List<Tag> tags = List.of(
                new Tag(1, "dog"),
                new Tag(2, "dog"),
                new Tag(3, "cat")
        );

        List<TagLink> tagLinks = List.of(
                new TagLink(11, 1, 100, TargetType.NODE),
                new TagLink(12, 1, 101, TargetType.SECTION),
                new TagLink(13, 1, 102, TargetType.FORM_ELEMENT),
                new TagLink(21, 2, 100, TargetType.NODE),
                new TagLink(22, 2, 101, TargetType.SECTION),
                new TagLink(23, 2, 202, TargetType.FORM_ELEMENT),
                new TagLink(31, 3, 300, TargetType.FORM_ELEMENT)
        );

        DeduplicateTagsAndLinks deduplicateTagsAndLinks = new DeduplicateTagsAndLinks();
        Map<Tag, List<TagLink>> resultMap = deduplicateTagsAndLinks.deduplicate(tags, tagLinks);

        // Check if we have the correct number of unique tags
        assertEquals(2, resultMap.size());

        // Iterate over the resultMap and verify if it is correct
        for(Map.Entry<Tag, List<TagLink>> entry : resultMap.entrySet()) {
            String tagName = entry.getKey().getName();
            List<TagLink> linkList = entry.getValue();

            switch (tagName) {
                case "dog":
                    assertEquals(4, linkList.size()); // there should be four links for "dog"
                    // Checking if all the redundant links have been removed
                    assertFalse(linkList.contains(new TagLink(21, 2, 100, TargetType.NODE)));
                    assertFalse(linkList.contains(new TagLink(22, 2, 101, TargetType.SECTION)));
                    break;

                case "cat":
                    assertEquals(1, linkList.size()); // there should be one link for "cat"
                    assertEquals(new TagLink(31, 3, 300, TargetType.FORM_ELEMENT), linkList.get(0));
                    break;
            }
        }
    }

}
