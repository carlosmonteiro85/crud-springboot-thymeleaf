package br.com.inicial.crud_sprinboot_thymeleaf.util;

public enum CryptographyValues {
	BYTE_ONE(0xff), BYTE_TWO(0x10), BYTE_THREE(0xFF);

    private Integer value;
    CryptographyValues(Integer value) {
        this.value = value;
    }
	public Integer getValue() {
		return value;
	}

}
