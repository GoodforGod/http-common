package io.goodforgod.http.common;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a media type.
 * <a href="https://www.iana.org/assignments/media-types/media-types.xhtml">Internet Assigned
 * Numbers Authority</a>
 * <a href="https://tools.ietf.org/html/rfc2046">Internet Engineering Task Force</a>
 *
 * @author Graeme Rocher
 * @author Anton Kurako (GoodforGod)
 * @since 15.02.2022
 */
public final class MediaType implements CharSequence {

    private record MediaTypeParsed(String name, BigDecimal quality, Map<String, String> parameters) {}

    /**
     * File extension used for Microsoft Excel Open XML Spreadsheet (XLSX).
     */
    private static final String EXTENSION_XLSX = "xlsx";

    /**
     * File extension for Microsoft Excel's workbook files in use between 97-2003.
     */
    private static final String EXTENSION_XLS = "xls";

    /**
     * A wildcard media type representing all types.
     */
    public static final String ALL = "*/*";

    /**
     * A wildcard media type representing all types.
     */
    public static final MediaType ALL_TYPE = new MediaType(ALL, "all");

    /**
     * Form encoded data: application/x-www-form-urlencoded.
     */
    public static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";

    /**
     * Form encoded data: application/x-www-form-urlencoded.
     */
    public static final MediaType APPLICATION_FORM_URLENCODED_TYPE = new MediaType(APPLICATION_FORM_URLENCODED);

    /**
     * Short cut for {@link #APPLICATION_FORM_URLENCODED_TYPE}.
     */
    public static final MediaType FORM = APPLICATION_FORM_URLENCODED_TYPE;

    /**
     * Multi part form data: multipart/form-data.
     */
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    /**
     * Multi part form data: multipart/form-data.
     */
    public static final MediaType MULTIPART_FORM_DATA_TYPE = new MediaType(MULTIPART_FORM_DATA);

    /**
     * HTML: text/html.
     */
    public static final String TEXT_HTML = "text/html";

    /**
     * HTML: text/html.
     */
    public static final MediaType TEXT_HTML_TYPE = new MediaType(TEXT_HTML, "html");

    /**
     * CSV: text/csv.
     */
    public static final String TEXT_CSV = "text/csv";

    /**
     * CSV: text/csv.
     */
    public static final MediaType TEXT_CSV_TYPE = new MediaType(TEXT_CSV, "csv");

    /**
     * XHTML: application/xhtml+xml.
     */
    public static final String APPLICATION_XHTML = "application/xhtml+xml";

    /**
     * XHTML: application/xhtml+xml.
     */
    public static final MediaType APPLICATION_XHTML_TYPE = new MediaType(APPLICATION_XHTML, "html");

    /**
     * XML: application/xml.
     */
    public static final String APPLICATION_XML = "application/xml";

    /**
     * XML: application/xml.
     */
    public static final MediaType APPLICATION_XML_TYPE = new MediaType(APPLICATION_XML, "xml");

    /**
     * JSON: text/json.
     */
    public static final String TEXT_JSON = "text/json";

    /**
     * JSON: text/json.
     */
    public static final MediaType TEXT_JSON_TYPE = new MediaType(TEXT_JSON, "json");

    /**
     * JSON: text/json;charset=utf-8
     */
    public static final String TEXT_JSON_UTF_8 = "text/json;charset=utf-8";

    /**
     * JSON: text/json;charset=utf-8
     */
    public static final MediaType TEXT_JSON_UTF_8_TYPE = new MediaType(TEXT_JSON_UTF_8, "json");

    /**
     * Plain Text: text/plain.
     */
    public static final String TEXT_PLAIN = "text/plain";

    /**
     * Plain Text: text/plain.
     */
    public static final MediaType TEXT_PLAIN_TYPE = new MediaType(TEXT_PLAIN, "txt");

    /**
     * Plain Text: text/plain;charset=utf-8
     */
    public static final String TEXT_PLAIN_UTF_8 = "text/plain;charset=utf-8";

    /**
     * Plain Text: text/plain;charset=utf-8
     */
    public static final MediaType TEXT_PLAIN_UTF_8_TYPE = new MediaType(TEXT_PLAIN_UTF_8, "txt");

    /**
     * JSON: application/json.
     */
    public static final String APPLICATION_JSON = "application/json";

    /**
     * JSON: application/json.
     */
    public static final MediaType APPLICATION_JSON_TYPE = new MediaType(APPLICATION_JSON, "json");

    /**
     * JSON: application/json;charset=utf-8
     */
    public static final String APPLICATION_JSON_UTF_8 = "application/json;charset=utf-8";

    /**
     * JSON: application/json;charset=utf-8
     */
    public static final MediaType APPLICATION_JSON_UTF_8_TYPE = new MediaType(APPLICATION_JSON_UTF_8, "json");

    /**
     * YAML: application/x-yaml.
     */
    public static final String APPLICATION_YAML = "application/x-yaml";

    /**
     * YAML: application/x-yaml.
     */
    public static final MediaType APPLICATION_YAML_TYPE = new MediaType(APPLICATION_YAML, "yaml");

    /**
     * YAML: application/x-yaml;charset=utf-8
     */
    public static final String APPLICATION_YAML_UTF_8 = "application/x-yaml;charset=utf-8";

    /**
     * YAML: application/x-yaml;charset=utf-8
     */
    public static final MediaType APPLICATION_YAML_UTF_8_TYPE = new MediaType(APPLICATION_YAML_UTF_8, "yaml");

    /**
     * XML: Microsoft Excel Open XML Spreadsheet (XLSX).
     */
    public static final String MICROSOFT_EXCEL_OPEN_XML = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    /**
     * XML: Microsoft Excel Open XML Spreadsheet (XLSX).
     */
    public static final MediaType MICROSOFT_EXCEL_OPEN_XML_TYPE = new MediaType(MICROSOFT_EXCEL_OPEN_XML, EXTENSION_XLSX);

    /**
     * Microsoft Excel's workbook files in use between 97-2003.
     */
    public static final String MICROSOFT_EXCEL = "application/vnd.ms-excel";

    /**
     * Microsoft Excel's workbook files in use between 97-2003.
     */
    public static final MediaType MICROSOFT_EXCEL_TYPE = new MediaType(MICROSOFT_EXCEL, EXTENSION_XLS);

    /**
     * XML: text/xml.
     */
    public static final String TEXT_XML = "text/xml";

    /**
     * XML: text/xml.
     */
    public static final MediaType TEXT_XML_TYPE = new MediaType(TEXT_XML, "xml");

    /**
     * Server Sent Event: text/event-stream.
     */
    public static final String TEXT_EVENT_STREAM = "text/event-stream";

    /**
     * Server Sent Event: text/event-stream.
     */
    public static final MediaType TEXT_EVENT_STREAM_TYPE = new MediaType(TEXT_EVENT_STREAM);

    /**
     * JSON Stream: application/x-json-stream.
     */
    public static final String APPLICATION_JSON_STREAM = "application/x-json-stream";

    /**
     * JSON Stream: application/x-json-stream.
     */
    public static final MediaType APPLICATION_JSON_STREAM_TYPE = new MediaType(APPLICATION_JSON_STREAM);

    /**
     * BINARY: application/octet-stream.
     */
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

    /**
     * BINARY: application/octet-stream.
     */
    public static final MediaType APPLICATION_OCTET_STREAM_TYPE = new MediaType(APPLICATION_OCTET_STREAM);

    /**
     * GraphQL: application/graphql.
     */
    public static final String APPLICATION_GRAPHQL = "application/graphql";

    /**
     * GraphQL: application/graphql.
     */
    public static final MediaType APPLICATION_GRAPHQL_TYPE = new MediaType(APPLICATION_GRAPHQL);

    /**
     * PDF: application/pdf.
     */
    public static final String APPLICATION_PDF = "application/pdf";

    /**
     * PDF: application/pdf.
     */
    public static final MediaType APPLICATION_PDF_TYPE = new MediaType(APPLICATION_PDF, "pdf");

    /**
     * Png Image: image/png.
     */
    public static final String IMAGE_PNG = "image/png";

    /**
     * Png Image: image/png.
     */
    public static final MediaType IMAGE_PNG_TYPE = new MediaType(IMAGE_PNG, "png");

    /**
     * Jpeg Image: image/jpeg.
     */
    public static final String IMAGE_JPEG = "image/jpeg";

    /**
     * Jpeg Image: image/jpeg.
     */
    public static final MediaType IMAGE_JPEG_TYPE = new MediaType(IMAGE_JPEG, "jpeg");

    /**
     * Gif Image: image/gif.
     */
    public static final String IMAGE_GIF = "image/gif";

    /**
     * Gif Image: image/gif.
     */
    public static final MediaType IMAGE_GIF_TYPE = new MediaType(IMAGE_GIF, "gif");

    /**
     * Webp Image: image/webp.
     */
    public static final String IMAGE_WEBP = "image/webp";

    /**
     * Webp Image: image/webp.
     */
    public static final MediaType IMAGE_WEBP_TYPE = new MediaType(IMAGE_WEBP, "webp");

    private final String name;
    private final String subtype;
    private final String type;
    private final String extension;
    private final String representation;
    private final Map<String, String> parameters;

    /**
     * See
     * https://docs.microsoft.com/en-us/dotnet/api/system.net.http.headers.mediatypewithqualityheadervalue?view=net-6.0
     */
    private final BigDecimal quality;

    /**
     * Constructs a new media type for the given string.
     *
     * @param name The name of the media type. For example application/json
     */
    private MediaType(@NotNull String name) {
        this(name, null);
    }

    /**
     * Constructs a new media type for the given string and extension.
     *
     * @param name      The name of the media type. For example application/json
     * @param extension The extension of the file using this media type if it differs from the subtype
     */
    private MediaType(@NotNull String name, @Nullable String extension) {
        final MediaTypeParsed mediaTypeParsed = parseMediaTypeName(name);
        this.name = mediaTypeParsed.name;
        this.quality = mediaTypeParsed.quality;

        final int i = this.name.indexOf('/');
        if (i > -1) {
            this.type = this.name.substring(0, i);
            this.subtype = this.name.substring(i + 1);
        } else {
            throw new IllegalArgumentException("Invalid mime type: " + name);
        }

        if (extension != null && !extension.isBlank()) {
            this.extension = extension;
        } else {
            int j = subtype.indexOf('+');
            this.extension = (j > -1)
                    ? subtype.substring(j + 1)
                    : subtype;
        }

        this.parameters = Collections.unmodifiableMap(mediaTypeParsed.parameters);
        this.representation = toStringRepresentation(mediaTypeParsed.parameters);
    }

    @NotNull
    private MediaTypeParsed parseMediaTypeName(@NotNull String name) {
        name = name.strip();
        final List<String> nameSplitBySemicolon = Arrays.stream(name.split(";"))
                .filter(s -> !s.isBlank())
                .toList();
        name = nameSplitBySemicolon.get(0).strip().toLowerCase();

        if (nameSplitBySemicolon.size() > 1) {
            BigDecimal q = BigDecimal.ONE;
            Map<String, String> parameters = Collections.emptyMap();
            for (int i = 1; i < nameSplitBySemicolon.size(); i++) {
                final String paramExpression = nameSplitBySemicolon.get(i);
                final int index = paramExpression.indexOf('=');

                if (index > -1) {
                    if (parameters.isEmpty()) {
                        parameters = new LinkedHashMap<>(4);
                    }

                    final String paramName = paramExpression.substring(0, index).trim();
                    final String paramValue = paramExpression.substring(index + 1).trim();

                    if ("q".equalsIgnoreCase(paramName)) {
                        q = new BigDecimal(paramValue);
                        parameters.put(paramName.toLowerCase(), paramValue);
                    } else if ("charset".equalsIgnoreCase(paramName)) {
                        final Charset charset = Charset.forName(paramValue);
                        parameters.put(paramName.toLowerCase(), charset.name());
                    } else {
                        parameters.put(paramName, paramValue);
                    }
                }
            }

            return new MediaTypeParsed(name, q, parameters);
        } else {
            return new MediaTypeParsed(name, BigDecimal.ONE, Collections.emptyMap());
        }
    }

    /**
     * Create a new or get a {@link MediaType} from the given text.
     *
     * @param mediaType The text
     * @return The {@link MediaType}
     */
    @NotNull
    public static MediaType of(@NotNull String mediaType) {
        return switch (mediaType) {
            case ALL -> ALL_TYPE;
            case APPLICATION_JSON -> APPLICATION_JSON_TYPE;
            case APPLICATION_YAML -> APPLICATION_YAML_TYPE;
            case TEXT_PLAIN -> TEXT_PLAIN_TYPE;
            case TEXT_JSON -> TEXT_JSON_TYPE;
            case TEXT_CSV -> TEXT_CSV_TYPE;
            case TEXT_XML -> TEXT_XML_TYPE;
            case APPLICATION_XML -> APPLICATION_XML_TYPE;
            case TEXT_EVENT_STREAM -> TEXT_EVENT_STREAM_TYPE;
            case APPLICATION_JSON_STREAM -> APPLICATION_JSON_STREAM_TYPE;
            case APPLICATION_OCTET_STREAM -> APPLICATION_OCTET_STREAM_TYPE;
            case APPLICATION_FORM_URLENCODED -> APPLICATION_FORM_URLENCODED_TYPE;
            case MULTIPART_FORM_DATA -> MULTIPART_FORM_DATA_TYPE;
            case TEXT_HTML -> TEXT_HTML_TYPE;
            case APPLICATION_XHTML -> APPLICATION_XHTML_TYPE;
            case APPLICATION_GRAPHQL -> APPLICATION_GRAPHQL_TYPE;
            case APPLICATION_PDF -> APPLICATION_PDF_TYPE;
            case IMAGE_PNG -> IMAGE_PNG_TYPE;
            case IMAGE_JPEG -> IMAGE_JPEG_TYPE;
            case IMAGE_GIF -> IMAGE_GIF_TYPE;
            case IMAGE_WEBP -> IMAGE_WEBP_TYPE;
            case APPLICATION_JSON_UTF_8 -> APPLICATION_JSON_UTF_8_TYPE;
            case APPLICATION_YAML_UTF_8 -> APPLICATION_YAML_UTF_8_TYPE;
            case TEXT_JSON_UTF_8 -> TEXT_JSON_UTF_8_TYPE;
            case TEXT_PLAIN_UTF_8 -> TEXT_PLAIN_UTF_8_TYPE;
            default -> new MediaType(mediaType);
        };
    }

    @NotNull
    public static MediaType of(@NotNull String mediaType, @NotNull String extension) {
        return new MediaType(mediaType, extension);
    }

    /**
     * @return The name of the mime type without any parameters
     */
    @NotNull
    public String name() {
        return name;
    }

    /**
     * @return The type of the media type. For example for application/hal+json this would return
     *             "application"
     */
    @NotNull
    public String type() {
        return type;
    }

    /**
     * @return The subtype. For example for application/hal+json this would return "hal+json"
     */
    @NotNull
    public String subtype() {
        return subtype;
    }

    /**
     * @return The extension. For example for application/hal+json this would return "json"
     */
    @NotNull
    public String extension() {
        return extension;
    }

    /**
     * @return The quality of the Mime type
     */
    @NotNull
    public String quality() {
        return quality.toString();
    }

    /**
     * @return The quality in BigDecimal form
     */
    @NotNull
    public BigDecimal qualityAsNumber() {
        return quality;
    }

    /**
     * @return The charset of the media type if specified
     */
    @NotNull
    public Optional<Charset> charset() {
        final String charset = parameters.get("charset");
        if (charset != null) {
            return Optional.of(Charset.forName(charset));
        }

        return Optional.empty();
    }

    @NotNull
    public Map<String, String> parameters() {
        return parameters;
    }

    @NotNull
    private String toStringRepresentation(Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return name;
        } else {
            final StringBuilder builder = new StringBuilder(name);
            parameters.forEach((paramName, value) -> builder.append(';').append(paramName).append('=').append(value));
            return builder.toString();
        }
    }

    /**
     * Create a new {@link MediaType} from the given text.
     *
     * @param mediaType The text
     * @return The {@link MediaType}
     */
    @NotNull
    public static MediaType of(@NotNull CharSequence mediaType) {
        return of(mediaType.toString());
    }

    /**
     * Resolve the {@link MediaType} for the given file extension.
     *
     * @param extension The file extension
     * @return The {@link MediaType}
     */
    @NotNull
    public static Optional<MediaType> ofExtension(@Nullable String extension) {
        if (extension != null && !extension.isEmpty()) {
            final String mimeType = MimeExtensions.getMime(extension);
            if (mimeType != null) {
                return Optional.of(new MediaType(mimeType, extension));
            }
        }

        return Optional.empty();
    }

    /**
     * Resolve the {@link MediaType} for the given file name. Defaults
     * to text/plain.
     *
     * @param filename The file name
     * @return The {@link MediaType}
     */
    @NotNull
    public static Optional<MediaType> ofFilename(@Nullable String filename) {
        if (filename != null && !filename.isEmpty()) {
            final String extension = extensionForFilename(filename);
            return ofExtension(extension);
        }

        return Optional.empty();
    }

    @Nullable
    private static String extensionForFilename(@NotNull String filename) {
        final int extensionPos = filename.lastIndexOf('.');
        if (extensionPos != -1) {
            final int lastUnixPos = filename.lastIndexOf('/');
            final int separator = (lastUnixPos == -1)
                    ? filename.lastIndexOf('\\')
                    : lastUnixPos;

            final int index = separator > extensionPos
                    ? -1
                    : extensionPos;

            if (index != -1) {
                return filename.substring(index + 1);
            }
        }

        return null;
    }

    @Override
    public int length() {
        return representation.length();
    }

    @Override
    public char charAt(int index) {
        return representation.charAt(index);
    }

    @NotNull
    @Override
    public CharSequence subSequence(int start, int end) {
        return representation.subSequence(start, end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MediaType mediaType = (MediaType) o;
        return name.equals(mediaType.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @NotNull
    @Override
    public String toString() {
        return representation;
    }
}
