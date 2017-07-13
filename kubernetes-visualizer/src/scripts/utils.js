/**
 * Truncate string length with '...'.
 * If left is true, the truncation is done at the end of the string.
 * Otherwise it is done at the beginning.
 *
 * @param str {string} The string.
 * @param length {number} The maximum length.
 * @param left {boolean} If true truncate at the end.
 */
function truncate(str, length, left) {
    if (!str) {
        return '';
    }

    if (str.length > length) {
        if (left) {
            return `${str.slice(0, length)}...`;
        }
        return `...${str.slice(str.length - length, str.length)}`;
    }
    return str;
}

/**
 * For each property in object.
 *
 * @param {Object} object The object.
 * @param {function(key, value)} delegate The function to call for each property-
 */
function forProperty(object, delegate) {
    Object.keys(object).forEach(key => {
        if (Object.hasOwnProperty.bind(object, key)) {
            delegate(key, object[key]);
        }
    });
}

/**
 * Extract version number from image name.
 * If no version number is present, return 'latest'.
 */
function extractVersion(image) {
    const temp = image.split(':');
    if (temp.length > 2) {
        return temp[2];
    } else if (temp.length > 1) {
        return temp[1];
    }
    return 'latest';
}

/**
 * Match object properties.
 * Return true if all properties of objectB excists on objectA.
 */
function matchObjects(objectA, objectB) {
    let match = true;
    forProperty(objectB, (key, value) => {
        if (objectA[key] !== value) {
            match = false;
        }
    });
    return match;
}
