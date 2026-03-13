package org.openapitools.configuration;

import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AssessmentOutcome;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AssessmentType;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.BulkSubmissionErrorCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.BulkSubmissionStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.CategoryCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.FeeCalculationType;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.MediationType;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ValidationMessageType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration(value = "org.openapitools.configuration.enumConverterConfiguration")
public class EnumConverterConfiguration {

    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.areaOfLawConverter")
    Converter<String, AreaOfLaw> areaOfLawConverter() {
        return new Converter<String, AreaOfLaw>() {
            @Override
            public AreaOfLaw convert(String source) {
                return AreaOfLaw.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.assessmentOutcomeConverter")
    Converter<String, AssessmentOutcome> assessmentOutcomeConverter() {
        return new Converter<String, AssessmentOutcome>() {
            @Override
            public AssessmentOutcome convert(String source) {
                return AssessmentOutcome.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.assessmentTypeConverter")
    Converter<String, AssessmentType> assessmentTypeConverter() {
        return new Converter<String, AssessmentType>() {
            @Override
            public AssessmentType convert(String source) {
                return AssessmentType.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.bulkSubmissionErrorCodeConverter")
    Converter<String, BulkSubmissionErrorCode> bulkSubmissionErrorCodeConverter() {
        return new Converter<String, BulkSubmissionErrorCode>() {
            @Override
            public BulkSubmissionErrorCode convert(String source) {
                return BulkSubmissionErrorCode.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.bulkSubmissionStatusConverter")
    Converter<String, BulkSubmissionStatus> bulkSubmissionStatusConverter() {
        return new Converter<String, BulkSubmissionStatus>() {
            @Override
            public BulkSubmissionStatus convert(String source) {
                return BulkSubmissionStatus.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.categoryCodeConverter")
    Converter<String, CategoryCode> categoryCodeConverter() {
        return new Converter<String, CategoryCode>() {
            @Override
            public CategoryCode convert(String source) {
                return CategoryCode.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.claimStatusConverter")
    Converter<String, ClaimStatus> claimStatusConverter() {
        return new Converter<String, ClaimStatus>() {
            @Override
            public ClaimStatus convert(String source) {
                return ClaimStatus.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.feeCalculationTypeConverter")
    Converter<String, FeeCalculationType> feeCalculationTypeConverter() {
        return new Converter<String, FeeCalculationType>() {
            @Override
            public FeeCalculationType convert(String source) {
                return FeeCalculationType.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.mediationTypeConverter")
    Converter<String, MediationType> mediationTypeConverter() {
        return new Converter<String, MediationType>() {
            @Override
            public MediationType convert(String source) {
                return MediationType.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.submissionStatusConverter")
    Converter<String, SubmissionStatus> submissionStatusConverter() {
        return new Converter<String, SubmissionStatus>() {
            @Override
            public SubmissionStatus convert(String source) {
                return SubmissionStatus.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.validationMessageTypeConverter")
    Converter<String, ValidationMessageType> validationMessageTypeConverter() {
        return new Converter<String, ValidationMessageType>() {
            @Override
            public ValidationMessageType convert(String source) {
                return ValidationMessageType.fromValue(source);
            }
        };
    }

}
