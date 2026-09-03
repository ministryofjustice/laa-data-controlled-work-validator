package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ResolvedClaimData unit tests")
class ResolvedClaimDataTest {

    @Test
    @DisplayName("accessors and toString return expected values")
    void accessorsAndToStringReturnExpectedValues() {
        ResolvedClaimData data = new ResolvedClaimData("calc", "area", "authCode", "desc");

        assertEquals("calc", data.feeCalculationType());
        assertEquals("area", data.feeSchemeAreaOfLaw());
        assertEquals("authCode", data.authorisedCategoryOfLawCode());
        assertEquals("desc", data.feeCodeDescription());

        String s = data.toString();
        // toString for records contains the component names and their values
        assertTrue(s.contains("feeCalculationType=calc"));
        assertTrue(s.contains("feeSchemeAreaOfLaw=area"));
        assertTrue(s.contains("authorisedCategoryOfLawCode=authCode"));
        assertTrue(s.contains("feeCodeDescription=desc"));
    }

    @Test
    @DisplayName("empty() factory returns an instance with all null fields")
    void emptyFactoryReturnsInstanceWithAllNullFields() {
        ResolvedClaimData empty = ResolvedClaimData.empty();

        assertNotNull(empty, "empty() should not return null");
        assertNull(empty.feeCalculationType());
        assertNull(empty.feeSchemeAreaOfLaw());
        assertNull(empty.authorisedCategoryOfLawCode());
        assertNull(empty.feeCodeDescription());

        String s = empty.toString();
        assertTrue(s.contains("feeCalculationType=null"));
        assertTrue(s.contains("feeSchemeAreaOfLaw=null"));
        assertTrue(s.contains("authorisedCategoryOfLawCode=null"));
        assertTrue(s.contains("feeCodeDescription=null"));
    }

    @Test
    @DisplayName("equals and hashCode behave as expected")
    void equalsAndHashCodeBehaveAsExpected() {
        ResolvedClaimData a = new ResolvedClaimData("c", "a", "auth", "d");
        ResolvedClaimData b = new ResolvedClaimData("c", "a", "auth", "d");
        ResolvedClaimData c = new ResolvedClaimData("c2", "a", "auth", "d");

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        assertNotEquals(a, c);
        // hashCode may collide in theory, but different objects with different state should usually differ
        assertNotEquals(a.hashCode(), c.hashCode());
    }

    @Test
    @DisplayName("instance is serializable and round-trips correctly")
    void instanceIsSerializableAndRoundTrips() throws Exception {
        ResolvedClaimData original = new ResolvedClaimData("calc", "area", "auth", "desc");

        byte[] bytes;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(original);
            oos.flush();
            bytes = bos.toByteArray();
        }

        Object read;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            read = ois.readObject();
        }

        ResolvedClaimData deserialized = assertInstanceOf(ResolvedClaimData.class, read);
        assertEquals(original, deserialized);
        assertEquals(original.hashCode(), deserialized.hashCode());
    }
}
