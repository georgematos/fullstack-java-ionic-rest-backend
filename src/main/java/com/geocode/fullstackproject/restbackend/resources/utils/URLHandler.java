package com.geocode.fullstackproject.restbackend.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * URLHandler
 */
public class URLHandler {

  public static String convertUTF8Param(String param) {
    try {
      return URLDecoder.decode(param, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }

  public static List<Long> parseLongStringParams(String params) {
    return Arrays.asList(params.split(",")).stream().map(s -> Long.parseLong(s)).collect(Collectors.toList());
  }
}
