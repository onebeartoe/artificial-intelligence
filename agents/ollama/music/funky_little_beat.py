
from music21 import stream, note #, quarterNote
import numpy as np
from scipy.io.wavfile import write
import librosa

# Define the tempo in beats per minute (BPM)
tempo = 120

# Generate a funky little beat sequence
beat_sequence = [
    [1, 0.5],
    [1, 0.25],
    [1, 0.5],
    [1, 0.25]
]

# Define the note duration in seconds
note_duration = 1.0

# Create a stream to hold our music notes
funky_beat_stream = stream.Stream()

# Add funky little beat rhythm using quarterNotes
for i, (duration, repeat) in enumerate(beat_sequence):
    for j in range(duration):
        funky_beat_stream.append(note.Note('C3', quarterLength=note_duration/4 * duration))
        if repeat:
            funky_beat_stream.append(note.Rest())

# Save the stream to a MIDI file
funky_beat_stream.write('midi', tempo=tempo, fp='funkylittlebeat.mid')

# Convert the MIDI to audio
audio = librosa.util.apply_midi_to_audio(funky_beat_stream)
audio = audio[0]  # Select the first channel (voice)

# Save the audio as a WAV file
librosa.output.write_wav('funkylittlebeat.wav', audio, 44100)

print("Funky little beat music generated and saved to current directory.")
