package com.example.i_partner_binet_task_job.utils;

public interface Variables {
    String API_URL = "https://bnet.i-partner.ru/";
    String API_TOKEN = "3tksn4U-1E-pECIHP9";

    String KEY_SESSION = "key_device_session";
    String KEY_PREFS = "key_prefs";

    String HEADER_TOKEN = "token: " + API_TOKEN;

    String METHOD_NEW_SESSION = "new_session";
    String METHOD_GET_ENTRIES = "get_entries";
    String METHOD_ADD_ENTRY = "add_entry";
}
