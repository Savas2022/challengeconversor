package models;

public record ConversorApi(
        String base_code,
        String target_code,
        double conversion_rate,
        double conversion_result
) {
}
