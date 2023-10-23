package org.example;

import java.util.*;

public class DeduplicateTagsAndLinks {
    public Map<Tag, List<TagLink>> deduplicate(List<Tag> tags, List<TagLink> tagLinks) {
        Map<String, Tag> uniqueTags = new HashMap<>();
        Map<Integer,String> uniqueTagNames = new HashMap<>();
        Set<Integer> uniqueTagIds = new HashSet<>();
        Map<String, TagLink> uniqueLinks = new HashMap<>();
        Map<Integer,Integer> tagIdToTagId = new HashMap<>();
        Map<Tag, List<TagLink>> resultMap = new LinkedHashMap<>();

        // Build a lookup for unique tags by name and build a lookup for unique tag names by id.
        for (Tag tag : tags) {
            if (!uniqueTags.containsKey(tag.getName())) {
                uniqueTags.put(tag.getName(), tag);
                uniqueTagNames.put(tag.getId(), tag.getName());
                uniqueTagIds.add(tag.getId());
                resultMap.put(tag, new ArrayList<>());
            }
            else {
                // If a tag with the same name already exists, update the tagIdToTagId lookup to reference the existing tag.
                tagIdToTagId.put(tag.getId(), uniqueTags.get(tag.getName()).getId());
            }
        }

        // Update tag links to reference unique tags and build a lookup for unique links by tagId, targetId, and targetType.
        for (TagLink link : tagLinks) {

            if(tagIdToTagId.containsKey(link.getTagId()))
                link.setTagId(tagIdToTagId.get(link.getTagId()));

            if (!uniqueTagIds.contains(link.getTagId()))
                continue;
            String uniqueKey = link.getTagId() + "|" + link.getTargetId() + "|" + link.getTargetType();

            if (!uniqueLinks.containsKey(uniqueKey)) {
                uniqueLinks.put(uniqueKey, link);
                String tagName = uniqueTagNames.get(link.getTagId());
                resultMap.get(uniqueTags.get(tagName)).add(link);
            }
        }

        return resultMap;
    }
}
