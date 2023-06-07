package kz.iitu.pcsystem.filtering;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import kz.iitu.pcsystem.entity.CPU;
import kz.iitu.pcsystem.entity.ComponentEntity;
import kz.iitu.pcsystem.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomSpecificationsBuilder<T extends ComponentEntity> {
    private final List<SearchCriteria> params = new ArrayList<>();

    public static <S extends ComponentEntity> Specification<S> getSpecification(String search) {
        System.out.println("SEARCH: " + search);

        CustomSpecificationsBuilder<S> builder = new CustomSpecificationsBuilder<>();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?)(;;;|$)", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ";;;");
        while (matcher.find()) {
            System.out.println("FOUND MATCHES: " + matcher.group(1) + " --- " + matcher.group(2) + " --- " + matcher.group(3));
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<S> spec = builder.build();
        return spec;
    }

    public CustomSpecificationsBuilder<T> with(String key, String op, Object value) {
        params.add(new SearchCriteria(key, op, value));
        return this;
    }

    public Specification<T> build() {
        if (params.isEmpty())
            return null;

        Specification<T> specification = (root, query, builder) -> {
            Join<T, Product> productJoin = root.join("products", JoinType.LEFT);
            query.groupBy(root.get("iid"));
            query.orderBy(builder.desc(builder.count(productJoin)));
            return null; // The actual predicate is not needed for this query
        };

        Specification<T> result = specification;

        for (int i = 0; i < params.size(); i++) {
            System.out.println("CRITERIA: " + params.get(i));
            result = Specification.where(result).and(new CustomSpecification<T>(params.get(i)));
        }

//        Specification<T> result = new CustomSpecification<T>(params.get(0));
//        System.out.println("CRITERIA: " + params.get(0));
//
//        for (int i = 1; i < params.size(); i++) {
//            System.out.println("CRITERIA: " + params.get(i));
//            result = Specification.where(result).and(new CustomSpecification<T>(params.get(i)));
//        }

        return result;
    }
}