package com.example.study.api.dto;

import java.util.List;

public record CategoryResponseDTO(String id,
                                  String name,
                                  List<ProductSummaryDTO> products) {}
