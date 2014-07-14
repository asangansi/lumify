package io.lumify.storm.video;

import io.lumify.core.util.LumifyLogger;
import io.lumify.core.util.LumifyLoggerFactory;
import org.json.JSONArray;
import org.json.JSONObject;

public class VideoUtils {
    private static final LumifyLogger LOGGER = LumifyLoggerFactory.getLogger(VideoUtils.class);

    /**
     * Creates a String[] to pass as options for running the ffmpeg process.
     * @return returns null when no rotation is needed for video.
     */
    public static String[] createFFMPEGRotationOptions(int videoRotation) {
        if (videoRotation == 90) {
            return new String[]{"-vf", "transpose=1"};
        } else if (videoRotation == 180) {
            return new String[]{"-vf", "transpose=1,transpose=1"};
        } else if (videoRotation == 270) {
            return new String[]{"-vf", "transpose=2"};
        } else {
            return null;
        }
    }

    public static Integer extractRotationFromJSON(JSONObject json) {
        Integer rotate = null;
        try {
            JSONArray streamsJson = json.optJSONArray("streams");
            JSONObject streamsIndex0Json = streamsJson.optJSONObject(0);
            JSONObject tagsJson = streamsIndex0Json.optJSONObject("tags");
            rotate = tagsJson.optInt("rotate");
        } catch (NullPointerException e) {
            LOGGER.info("Could not retrieve a \"rotate\" value from the JSON object.");
        }
        return rotate;
    }


    /*
    public static int retrieveVideoRotation(){
        return 0;
    }
    */
}
