package com.ajbose.learning.model;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import javax.validation.Valid;


/**
 * Describes an end-point. It contains the URL and the response.
 **/
import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
@ApiModel(description = "Describes an end-point. It contains the URL and the response.")

public class EndPoint   {
  
  private @Valid String url = null;
  private @Valid String response = null;

  /**
   **/
  public EndPoint url(String url) {
    this.url = url;
    return this;
  }

  
  @ApiModelProperty(example = "/v1/getProducts", value = "")
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   **/
  public EndPoint response(String response) {
    this.response = response;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("response")
  public String getResponse() {
    return response;
  }
  public void setResponse(String response) {
    this.response = response;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EndPoint endPoint = (EndPoint) o;
    return Objects.equals(url, endPoint.url) &&
        Objects.equals(response, endPoint.response);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, response);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EndPoint {\n");
    
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    response: ").append(toIndentedString(response)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

